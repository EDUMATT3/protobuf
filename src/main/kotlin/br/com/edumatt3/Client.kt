package br.com.edumatt3

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
    val client = EmployeeServiceGrpc.newBlockingStub(channel)

    val request = EmployeeRequest.newBuilder()
        .setName("fulano")
        .setCpf("000.000.000.-00")
        .setAge(20)
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

    val response = client.register(request)

    println(response)
}