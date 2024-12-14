package com.example.kotlindersleri.homeworks

class Homework2Class {
    fun icAci () : Int {
        // Soru 1 :
        println("2'den büyük bir kenar sayısı giriniz : ")
        var kenarSayisi = readLine()?.toIntOrNull()

        while (kenarSayisi == null || kenarSayisi < 3) {
            println("Lütfen 2'den büyük bir kenar sayısı giriniz : ")
            kenarSayisi = readLine()?.toIntOrNull()
        }

        val icAci = ((kenarSayisi - 2) * 180)
        print("İç açıları toplamı : $icAci")
        return icAci

    }

    // Soru 2 :
    fun maas () : Int {
        println("Lütfen çalışma günü giriniz : ")
        var gun = readLine()?.toIntOrNull()

        while (gun == null || gun < 0) {
            println("Lütfen geçerli bir gün giriniz : ")
            gun = readLine()?.toIntOrNull()
        }

        val saat = gun * 8

        val ucret = 40
        val mesaiUcret = 80
        val mesaiBaslangıc = 150

        return if(saat < mesaiBaslangıc) {
            println("Maaş : ${ucret  * saat}")
            ucret * saat
        }else {
            val mesaiSaati = saat - mesaiBaslangıc
            println("Maaş : ${(ucret * mesaiBaslangıc) + (mesaiUcret * mesaiSaati) } ")
            (ucret * mesaiBaslangıc) + (mesaiUcret * mesaiSaati)
        }

    }

    // Soru 3 :
    fun otopark () : Int {
        println("Lütfen otoparkta kalma saati giriniz : ")
        var saat = readLine()?.toIntOrNull()

        while (saat == null || saat < 0) {
            println("Lütfen geçerli bir otoparkta kalma saati giriniz : ")
            saat = readLine()?.toIntOrNull()
        }

        return if (saat <= 1 ) {
            println("Otopark ücreti : 50")
            50
        }else {
            println("Otopark ücreti : ${50 + (saat - 1) * 10}")
            50 + (saat - 1) * 10
        }
    }

    // Soru 4 :
    fun kmToMile () : Double {
        println("Lütfen km giriniz : ")
        var km = readLine()?.toDoubleOrNull()

        while (km == null || km < 0) {
            println("Lütfen geçerli bir km değeri giriniz : ")
            km = readLine()?.toDoubleOrNull()
        }

        println("$km kilometre = ${km * 0.621} mil ")
        return km * 0.621
    }

    // Soru 5 :
    fun dikdortgen () : Double {
        println("Lütfen kısa kenar giriniz : ")
        var kısaKenar = readLine()?.toDoubleOrNull()

        while (kısaKenar == null || kısaKenar < 0) {
            println("Lütfen geçerli bir kısa kenar giriniz : ")
            kısaKenar = readLine()?.toDoubleOrNull()
        }

        println("Lütfen uzun kenar giriniz : ")
        var uzunKenar = readLine()?.toDoubleOrNull()

        while (uzunKenar == null || uzunKenar < 0) {
            println("Lütfen geçerli bir uzun kenar giriniz : ")
            uzunKenar = readLine()?.toDoubleOrNull()
        }

        println("Dikdörtgenin alanı : ${kısaKenar * uzunKenar}")
        return kısaKenar * uzunKenar
    }

    // Soru 6 :
    fun faktoriyel () : Long {
        println("Lütfen sayı giriniz : ")
        var sayi = readLine()?.toIntOrNull()

        while (sayi == null || sayi <0) {
            println("Lütfen geçerli bir sayı giriniz : ")
            sayi = readLine()?.toIntOrNull()
        }

        var faktoriyel : Long = 1
        for (i in 1..sayi) {
            faktoriyel *= i
        }
        println("Sayının faktöriyeli : $faktoriyel")
        return faktoriyel
    }

    // Soru 7 :
    fun e () : Int {
        println("Lütfen input giriniz : ")
        var input = readLine()?:""

        var count = 0
        for (c in input) {
            if (c.lowercaseChar() == 'e')
                count++
        }
        println("İnput içerisindeki 'E/e' sayısı : $count")
        return count

    }
}