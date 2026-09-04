package com.example.freeapp.presentation.screens.carousel
//imports
import com.example.freeapp.R

data class CarouselPage(
    val image: Int,
    val title: String,
    val description: String
)

val carouselPages = listOf(

    CarouselPage(
        image = R.drawable.img_carousel1,
        title = "Freelancer Trabalho",
        description = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    ),

    CarouselPage(
        image = R.drawable.img_carousel2,
        title = "Freelancer Trabalho",
        description = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    ),

    CarouselPage(
        image = R.drawable.img_carousel3,
        title = "Freelancer Trabalho",
        description = "Lorem ipsum dolor sit amet consectetur. Sed tristique ultrices cras dictum vel. Ac dictum pharetra ut non vel senectus bibendum ipsum i"
    )
)