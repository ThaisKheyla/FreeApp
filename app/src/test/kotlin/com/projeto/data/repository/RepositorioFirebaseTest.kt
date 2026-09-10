package com.projeto.data.repository

import com.example.freeapp.data.repository.RepositorioFirebase
import com.example.freeapp.domain.PersonalData
import com.example.freeapp.domain.User
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

class RepositorioFirebaseTest {

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
}
