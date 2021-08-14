package br.com.edumatt3

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    val request = EmployeeRequest.newBuilder()
        .setName("fulano")
        .setCpf("000.000.000.-00")
        .setWage(2000.0)
        .setIsActive(true)
        .setRole(Role.DEV)
        .addAddresses(
            EmployeeRequest.Address.newBuilder()
                .setStreet("rua fulano de tal")
                .setZipcode("00000-000")
                .setComplement("any")
                .build()
        )
        .build()

    //o valor role dev não é enviado porque é o 0/default, quando n é recebido já se sabe que é o padrão
    println(request)

    //write object on disk
    request.writeTo(FileOutputStream("employee-request.bin"))

    //read from disk
    val request2 = EmployeeRequest.newBuilder().mergeFrom(FileInputStream("employee-request.bin"))

    request2.setRole(Role.MANAGER)
        .build()

    println(request2)
}