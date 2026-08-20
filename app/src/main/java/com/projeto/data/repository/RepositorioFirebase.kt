package com.projeto.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.projeto.domain.model.Usuario
import kotlinx.coroutines.tasks.await

class RepositorioFirebase(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun cadastrar(usuario: Usuario): Result<Unit> {
        return try {
            val resultado = auth.createUserWithEmailAndPassword(
                usuario.email.trim(),
                usuario.senha
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

    private fun Usuario.toFirestoreMap(): Map<String, String> {
        return mapOf(
            "nome" to nome,
            "dataNascimento" to dataNascimento,
            "cpf" to cpf,
            "email" to email,
            "telefone" to telefone,
            "cep" to cep,
            "endereco" to endereco,
            "numero" to numero,
            "complemento" to complemento,
            "bairro" to bairro,
            "cidade" to cidade,
            "estado" to estado,
            "profissao" to profissao,
            "especialidade" to especialidade,
            "regiao" to regiao,
            "horario" to horario,
            "agencia" to agencia,
            "conta" to conta,
            "tipoConta" to tipoConta,
            "pix" to pix,
            "opcaoPagamento" to opcaoPagamento
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
