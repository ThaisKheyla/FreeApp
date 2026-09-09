package com.example.freeapp.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.freeapp.data.remote.dto.RequisicaoCadastro

class CadastroFirebaseDataSource {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun cadastrarUsuario(
        dados: RequisicaoCadastro,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(dados.email, dados.password)
            .addOnSuccessListener { resultado ->
                val uid = resultado.user?.uid

                if (uid == null) {
                    onError(Exception("Usuário não encontrado"))
                    return@addOnSuccessListener
                }

                val usuario = hashMapOf(
                    "nome" to dados.name,
                    "dataNascimento" to dados.birthDate,
                    "cpf" to dados.cpf,
                    "email" to dados.email,
                    "telefone" to dados.phone,
                    "cep" to dados.zipCode,
                    "endereco" to dados.street,
                    "numero" to dados.number,
                    "complemento" to dados.complement,
                    "bairro" to dados.neighborhood,
                    "cidade" to dados.city,
                    "estado" to dados.state,
                    "profissao" to dados.profession,
                    "especialidade" to dados.specialty,
                    "regiao" to dados.region,
                    "horario" to dados.schedule,
                    "agencia" to dados.agency,
                    "conta" to dados.account,
                    "tipoConta" to dados.accountType,
                    "pix" to dados.pix,
                    "opcaoPagamento" to dados.paymentOption,
                    "ultimos4DigitosCartao" to dados.cardNumber.takeLast(4),
                    "validadeCartao" to dados.cardExpiration
                )

                firestore.collection("usuarios")
                    .document(uid)
                    .set(usuario)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onError(it)
                    }
            }
            .addOnFailureListener {
                onError(it)
            }
    }
}