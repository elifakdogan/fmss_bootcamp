package com.example.kotlindersleri.homeworks

fun main() {
    val homework = Homework2Class()

    println("Lütfen metod seçiniz : ")
    println("1 : İç açı hesaplama")
    println("2 : Maaş hesaplama")
    println("3 : Otopark ücreti hesaplama")
    println("4 : Km'den mil hesaplama")
    println("5 : Dikdörtgen alanı hesaplama")
    println("6 : Faktöriyel hesaplama")
    println("7 : E/e harfi sayısı bulma")

    var secim = readLine()?.toIntOrNull()
    while (secim == null || secim < 1 || secim > 7) {
        println("Lütfen geçerli bir seçim yapın (1 ile 7 arasında) :")
        secim = readLine()?.toIntOrNull()
    }

    when(secim) {
        1 -> homework.icAci()
        2 -> homework.maas()
        3 -> homework.otopark()
        4 -> homework.kmToMile()
        5 -> homework.dikdortgen()
        6 -> homework.faktoriyel()
        7 -> homework.e()
    }
}
