package com.example.freeapp.data.firebase

import com.example.freeapp.data.remote.dto.RegisterRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRegisterDataSource(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    fun registerUser(
        data: RegisterRequest,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(
            data.email,
            data.password
        )
            .addOnSuccessListener { result ->

                val uid = result.user?.uid

                if (uid == null) {
                    onError(
                        Exception("Usuário não encontrado")
                    )
                    return@addOnSuccessListener
                }

                val user = hashMapOf(
                    "name" to data.name,
                    "birthDate" to data.birthDate,
                    "cpf" to data.cpf,
                    "email" to data.email,
                    "phone" to data.phone,
                    "zipCode" to data.zipCode,
                    "address" to data.address,
                    "number" to data.number,
                    "complement" to data.complement,
                    "district" to data.district,
                    "city" to data.city,
                    "state" to data.state,
                    "profession" to data.profession,
                    "specialty" to data.specialty,
                    "region" to data.region,
                    "schedule" to data.schedule,
                    "agency" to data.agency,
                    "account" to data.account,
                    "accountType" to data.accountType,
                    "pix" to data.pix,
                    "paymentMethod" to data.paymentMethod,
                    "last4CardDigits" to data.cardNumber.takeLast(4),
                    "cardExpiration" to data.cardExpiration
                )

                firestore.collection("users")
                    .document(uid)
                    .set(user)
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