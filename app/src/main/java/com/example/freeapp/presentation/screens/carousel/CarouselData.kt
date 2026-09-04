package com.example.freeapp.presentation.screens.carousel
//imports
import com.example.freeapp.R

data class CarouselPage(
    val imagem: Int,
    val titulo: String,
    val descricao: String
)

val carouselPages = listOf(

    CarouselPage(
        imagem = R.drawable.img_carousel1,
        titulo = "Freelancer Trabalho",
        descricao = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    ),

    CarouselPage(
        imagem = R.drawable.img_carousel2,
        titulo = "Freelancer Trabalho",
        descricao = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    ),

    CarouselPage(
        imagem = R.drawable.img_carousel3,
        titulo = "Freelancer Trabalho",
        descricao = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    )
)
