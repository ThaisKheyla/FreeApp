package com.example.freeapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.example.freeapp.domain.User
import kotlinx.coroutines.tasks.await

class RepositorioFirebase(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    
    suspend fun cadastrar(usuario: User): Result<Unit> {
        return try {
            val resultado = auth.createUserWithEmailAndPassword(
                usuario.email.trim(),
                usuario.password
            ).await()

            val uid = resultado.user?.uid
                ?: return Result.failure(Exception("Usuário não identificado."))

            firestore
                .collection("usuarios")
                .document(uid)
                .set(usuario.toFirestoreMap())
                .await()

            Result.success(Unit)
        } catch (erro: Exception) {
            Result.failure(Exception(mapearMensagem(erro)))
        }
    }

    suspend fun login(email: String, senha: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email.trim(), senha).await()
            Result.success(Unit)
        } catch (erro: Exception) {
            Result.failure(Exception(mapearMensagem(erro)))
        }
    }

    suspend fun buscarNomeUsuarioAtual(): Result<String> {
        return try {
            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("Usuário não autenticado."))

            val nome = firestore
                .collection("usuarios")
                .document(uid)
                .get()
                .await()
                .getString("nome")
                .orEmpty()

            Result.success(nome)
        } catch (erro: Exception) {
            Result.failure(erro)
        }
    }

    suspend fun redefinirSenha(email: String): Result<Unit> {
        return try {
            auth.sendPasswordResetEmail(email.trim()).await()
            Result.success(Unit)
        } catch (erro: Exception) {
            Result.failure(Exception(mapearMensagem(erro)))
        }
    }

    fun sair() {
        auth.signOut()
    }

    private fun User.toFirestoreMap(): Map<String, String> {
        return mapOf(
            "nome" to name,
            "dataNascimento" to birthDate,
            "cpf" to cpf,
            "email" to email,
            "telefone" to phone,
            "cep" to zipCode,
            "endereco" to street,
            "numero" to number,
            "complemento" to complement,
            "bairro" to neighborhood,
            "cidade" to city,
            "estado" to state,
            "profissao" to profession,
            "especialidade" to specialty,
            "regiao" to region,
            "horario" to schedule,
            "agencia" to agency,
            "conta" to account,
            "tipoConta" to accountType,
            "pix" to pix,
            "opcaoPagamento" to paymentOption,
            "ultimos4DigitosCartao" to cardNumber.takeLast(4),
            "validadeCartao" to cardExpiration
        )
    }

    private fun mapearMensagem(erro: Exception): String {
        val codigo = (erro as? FirebaseAuthException)?.errorCode

        return when (codigo) {
            "ERROR_INVALID_EMAIL" -> "Informe um e-mail válido."
            "ERROR_EMAIL_ALREADY_IN_USE" -> "Este e-mail já está cadastrado."
            "ERROR_INVALID_CREDENTIAL",
            "ERROR_WRONG_PASSWORD",
            "ERROR_USER_NOT_FOUND" -> "E-mail ou senha inválidos."
            "ERROR_WEAK_PASSWORD" -> "A senha precisa ter pelo menos 6 caracteres."
            "ERROR_USER_DISABLED" -> "Esta conta está desativada."
            else -> erro.message ?: "Não foi possível concluir a operação."
        }
    }
}
