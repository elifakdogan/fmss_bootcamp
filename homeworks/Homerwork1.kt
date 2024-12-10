package com.example.kotlindersleri.homeworks

fun main() {
    //Location Information
    val district = "Üsküdar"
    val continent = "Asia"
    val postalCode = "34000"
    val latitude = 56.8751
    val longitude = 27.5623
    var neighborhoodName = "Altunizade"

    println("Yaşadığı İlçe Adı : $district")
    println("İlçenin Bulunduğu Kıta : $continent")
    println("Posta Kodu : $postalCode")
    println("Enlem : $latitude")
    println("Boylam : $longitude")
    println("Yaşadığı Mahalle Adı : $neighborhoodName")

    //Contact Information
    val fax = "02164567896"
    var instagramAddress = "@elifelif"

    println("Faks Numarası : $fax")
    println("Instagram Adresi : $instagramAddress")

    //Work and Company Information
    var department = "Information Technologies"
    val company = "Tech Company"
    var computerModel = "HP"

    println("Çalıştığı Bölüm : $department")
    println("Çalıştığı Şirket Adı : $company")
    println("Kulllandığı Bilgisayar Markası : $computerModel")

    //Payment and Finance Information
    var paymentTime = "15:10"
    var eftAmount = 2600.00
    var paymentAmount = 350.00
    var debt = 25000.00
    var raiseAmount = 1200.50
    val cargoTrackingNumber = "ty123456789"
    var couponDuration = 20
    val couponCode = "LEZZET100"
    val invoiceDate = "05.12.2024"

    println("Ödeme Saati : $paymentTime")
    println("EFT Miktarı : $eftAmount")
    println("Ödeme Miktarı : $paymentAmount")
    println("Borç Bilgisi : $debt")
    println("Zam Miktarı : $raiseAmount")
    println("Kargo Takip No : $cargoTrackingNumber")
    println("Kupon Süresi : $couponDuration")
    println("Kuppon Kodu : $couponCode")
    println("Fatura Tarihi : $invoiceDate")

    //Product Information
    var productQuantity = 200
    var soldQuantity = 30
    var productPrice = 94.99

    println("Ürün Miktarı : $productQuantity")
    println("Satılan Miktarı : $soldQuantity")
    println("Ürün Fiyatı : $productPrice")

    //Personal Information
    val customerLastName = "Akdoğan"
    val dateOFBirth = "28.03.2000"
    var maritalStatus = "Bekar"

    println("Müşteri Soyadı : $customerLastName")
    println("Doğum Tarihi : $dateOFBirth")
    println("Medeni Durumu : $maritalStatus")

    //Date and Time Information
    var eventDay = "Perşembe"
    var paymentDay = "Pazartesi"
    val travelDepartureDay = "Pazar"
    val publicationDate = "15.08.2021"
    var usageTime = "12"

    println("Etkinlik Günü : $eventDay")
    println("Ödeme Günü : $paymentDay")
    println("Yolculuk Çıkış Tarihi : $travelDepartureDay")
    println("Basım Tarihi : $publicationDate")
    println("Kullanım Süresi : $usageTime ay")

    //Food and Restaurant Information
    val mealName = "Kebap"
    var venueRating = 8.7

    println("Yemek Adı : $mealName")
    println("Mekan Puanı : $venueRating")

    //Technology and Media Information
    var phoneModel = "iPhone 15"
    val magazineName = "Bilim"
    val videoName = "Bootcamp 1. Ders Kaydı"
    var videoComment = "Harika ders"
    val musicDuration = 90
    val fileName = "Kotlin.pdf"
    val imageFormat = "PNG"
    val color = "Mavi"
    val colorCode = "#fff"
    val screenSize = 15.6
    var minutesUsed = 45

    println("Telefon Modeli : $phoneModel")
    println("Dergi Adı : $magazineName")
    println("Video Adı : $videoName")
    println("Video Yorumu : $videoComment")
    println("Müzik Süresi : $musicDuration saniye")
    println("Dosya Adı : $fileName")
    println("Resim Formatı : $imageFormat")
    println("Renk : $color")
    println("Renk Kodu : $colorCode")
    println("Ekran Boyutu : $screenSize inc")
    println("Kullanılan Dakika : $minutesUsed dakika")

    //Environmental Information
    var pressure = 101.25

    println("Basınç Bilgisi : $pressure")

    //Other Information
    val numberOfApartments = 8
    val busRoute = "12A"

    println("Daire Sayısı : $numberOfApartments")
    println("Otobüs Hattı : $busRoute")
}