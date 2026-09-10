package com.projeto.data.repository

import com.example.freeapp.data.firebase.FirebaseRegisterDataSource
import com.example.freeapp.data.remote.dto.RegisterRequest
import com.example.freeapp.data.repository.RepositorioFirebase
import com.example.freeapp.domain.PersonalData
import com.example.freeapp.domain.User
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RepositorioAutenticacaoTest {

    private val auth = mockk<FirebaseAuth>()
    private val firestore = mockk<FirebaseFirestore>()
    private val repository = RepositorioFirebase(auth, firestore)

    @Test
    fun cadastrar_quandoFirebaseConclui_deveRetornarSucessoEPersistirUsuario() = runTest {
        val authResult = mockk<AuthResult>()
        val firebaseUser = mockk<FirebaseUser>()
        val usuarios = mockk<CollectionReference>()
        val documento = mockk<DocumentReference>()
        val savedUser = slot<Map<String, String>>()
        val user = User(
            personalData = PersonalData(
                name = "Ana",
                email = " ana@email.com ",
                password = "123456"
            )
        )

        every { auth.createUserWithEmailAndPassword("ana@email.com", "123456") } returns Tasks.forResult(authResult)
        every { authResult.user } returns firebaseUser
        every { firebaseUser.uid } returns "uid-123"
        every { firestore.collection("usuarios") } returns usuarios
        every { usuarios.document("uid-123") } returns documento
        every { documento.set(capture(savedUser)) } returns Tasks.forResult(null)

        val result = repository.cadastrar(user)

        assertTrue(result.isSuccess)
        verify { documento.set(savedUser.captured) }
        assertEquals("Ana", savedUser.captured["nome"])
        assertEquals(" ana@email.com ", savedUser.captured["email"])
    }

    @Test
    fun login_quandoFirebaseFalha_deveRetornarMensagemDoErro() = runTest {
        val error = IllegalStateException("falha no login")
        every { auth.signInWithEmailAndPassword("ana@email.com", "123456") } returns Tasks.forException(error)

        val result = repository.login(" ana@email.com ", "123456")

        assertTrue(result.isFailure)
        assertEquals("falha no login", result.exceptionOrNull()?.message)
    }

    @Test
    fun registerUser_quandoFirebaseConclui_deveChamarSucessoEGravarDados() {
        val authResult = mockk<AuthResult>()
        val firebaseUser = mockk<FirebaseUser>()
        val authTask = mockk<Task<AuthResult>>()
        val firestoreTask = mockk<Task<Void>>()
        val users = mockk<CollectionReference>()
        val document = mockk<DocumentReference>()
        val savedUser = slot<Map<String, Any>>()
        val onSuccess = mockk<() -> Unit>(relaxed = true)
        val onError = mockk<(Exception) -> Unit>(relaxed = true)
        val request = request()
        val dataSource = FirebaseRegisterDataSource(auth, firestore)

        every { auth.createUserWithEmailAndPassword(request.email, request.password) } returns authTask
        every { authTask.addOnSuccessListener(any()) } answers {
            firstArg<OnSuccessListener<AuthResult>>().onSuccess(authResult)
            authTask
        }
        every { authTask.addOnFailureListener(any()) } returns authTask
        every { authResult.user } returns firebaseUser
        every { firebaseUser.uid } returns "uid-123"
        every { firestore.collection("users") } returns users
        every { users.document("uid-123") } returns document
        every { document.set(capture(savedUser)) } returns firestoreTask
        every { firestoreTask.addOnSuccessListener(any()) } answers {
            firstArg<OnSuccessListener<Void>>().onSuccess(null)
            firestoreTask
        }
        every { firestoreTask.addOnFailureListener(any()) } returns firestoreTask

        dataSource.registerUser(request, onSuccess, onError)

        verify(exactly = 1) { onSuccess() }
        verify(exactly = 0) { onError(any()) }
        assertEquals("Ana", savedUser.captured["name"])
        assertEquals("5678", savedUser.captured["last4CardDigits"])
    }

    @Test
    fun registerUser_quandoAuthFalha_deveChamarErro() {
        val error = IllegalStateException("falha no cadastro")
        val authTask = mockk<Task<AuthResult>>()
        val onSuccess = mockk<() -> Unit>(relaxed = true)
        val onError = mockk<(Exception) -> Unit>(relaxed = true)
        val request = request()
        val dataSource = FirebaseRegisterDataSource(auth, firestore)

        every { auth.createUserWithEmailAndPassword(request.email, request.password) } returns authTask
        every { authTask.addOnSuccessListener(any()) } returns authTask
        every { authTask.addOnFailureListener(any()) } answers {
            firstArg<OnFailureListener>().onFailure(error)
            authTask
        }

        dataSource.registerUser(request, onSuccess, onError)

        verify(exactly = 0) { onSuccess() }
        verify { onError(error) }
    }

    @Test
    fun registerUser_quandoNaoRetornaUsuario_deveInformarErro() {
        val authResult = mockk<AuthResult>()
        val authTask = mockk<Task<AuthResult>>()
        val onSuccess = mockk<() -> Unit>(relaxed = true)
        val onError = mockk<(Exception) -> Unit>(relaxed = true)
        val request = request()
        val dataSource = FirebaseRegisterDataSource(auth, firestore)

        every { auth.createUserWithEmailAndPassword(request.email, request.password) } returns authTask
        every { authTask.addOnSuccessListener(any()) } answers {
            firstArg<OnSuccessListener<AuthResult>>().onSuccess(authResult)
            authTask
        }
        every { authTask.addOnFailureListener(any()) } returns authTask
        every { authResult.user } returns null

        dataSource.registerUser(request, onSuccess, onError)

        verify { onError(match { it.message == "Usuário não encontrado" }) }
        verify(exactly = 0) { onSuccess() }
    }

    private fun request() = RegisterRequest(
        name = "Ana",
        birthDate = "01/01/1990",
        cpf = "12345678900",
        email = "ana@email.com",
        phone = "11999999999",
        password = "123456",
        zipCode = "01001000",
        address = "Rua A",
        number = "10",
        complement = "Apto 1",
        district = "Centro",
        city = "Sao Paulo",
        state = "SP",
        profession = "Designer",
        specialty = "UI",
        region = "Sudeste",
        schedule = "Comercial",
        agency = "1234",
        account = "56789",
        accountType = "Corrente",
        pix = "ana@email.com",
        paymentMethod = "Cartao",
        cardNumber = "1234567812345678",
        cardExpiration = "12/30",
        cvv = "123"
    )
}
