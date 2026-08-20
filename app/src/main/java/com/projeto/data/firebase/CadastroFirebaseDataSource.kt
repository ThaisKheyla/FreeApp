package com.projeto.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.projeto.data.remote.dto.RequisicaoCadastro

class CadastroFirebaseDataSource {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun cadastrarUsuario(
        dados: RequisicaoCadastro,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(dados.email, dados.senha)
            .addOnSuccessListener { resultado ->
                val uid = resultado.user?.uid

                if (uid == null) {
                    onError(Exception("Usuário não encontrado"))
                    return@addOnSuccessListener
                }

                val usuario = hashMapOf(
                    "nome" to dados.nome,
                    "dataNascimento" to dados.dataNascimento,
                    "cpf" to dados.cpf,
                    "email" to dados.email,
                    "telefone" to dados.telefone,
                    "cep" to dados.cep,
                    "endereco" to dados.endereco,
                    "numero" to dados.numero,
                    "complemento" to dados.complemento,
                    "bairro" to dados.bairro,
                    "cidade" to dados.cidade,
                    "estado" to dados.estado,
                    "profissao" to dados.profissao,
                    "especialidade" to dados.especialidade,
                    "regiao" to dados.regiao,
                    "horario" to dados.horario,
                    "agencia" to dados.agencia,
                    "conta" to dados.conta,
                    "tipoConta" to dados.tipoConta,
                    "pix" to dados.pix,
                    "opcaoPagamento" to dados.opcaoPagamento,
                    "ultimos4DigitosCartao" to dados.numeroCartao.takeLast(4),
                    "validadeCartao" to dados.validadeCartao
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