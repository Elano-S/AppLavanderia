fun main() {
    println("======================================")
    println("        APP DE LAVANDERIA")
    println("======================================")

    print("Nome do cliente: ")
    val nomeCliente = readLine()?.trim().orEmpty()
    val cliente = Cliente(nomeCliente)

    print("Quantas peças de roupa serão lavadas? ")
    val quantidadeRoupas = readLine()?.toIntOrNull() ?: 0

    val roupas = mutableListOf<Roupa>()

    for (i in 1..quantidadeRoupas) {
        print("Descrição da roupa $i: ")
        val descricao = readLine()?.trim().orEmpty()

        print("Peso da roupa $i (kg): ")
        val peso = readLine()
            ?.replace(',', '.')
            ?.toDoubleOrNull() ?: 0.0

        roupas.add(Roupa(descricao, peso))
    }

    println("\nEscolha o tipo de lavagem:")
    println("1 - Seco        (R$ 15,00/kg)")
    println("2 - Delicada    (R$ 12,00/kg)")
    println("3 - Pesada      (R$ 10,00/kg)")

    print("Opção: ")
    val tipoLavagem = readLine()?.toIntOrNull() ?: 0

    if (tipoLavagem !in 1..3) {
        println("Tipo de lavagem inválido.")
        return
    }

    print("Deseja removedor de manchas? (S/N): ")
    val desejaRemovedor = readLine()
        ?.trim()
        ?.uppercase()

    val removedorManchas: String? = if (desejaRemovedor == "S") {
        print("Informe o tipo de removedor: ")
        readLine()?.trim()?.takeIf { it.isNotEmpty() }
    } else {
        null
    }

    print("Quantos itens adicionais deseja incluir? ")
    val quantidadeAdicionais = readLine()?.toIntOrNull() ?: 0

    val adicionais = mutableListOf<Double>()

    for (i in 1..quantidadeAdicionais) {
        print("Valor do adicional $i (R$): ")
        val valor = readLine()
            ?.replace(',', '.')
            ?.toDoubleOrNull() ?: 0.0

        adicionais.add(valor)
    }

    val pedido = PedidoLavanderia(
        cliente = cliente,
        roupas = roupas,
        tipoLavagem = tipoLavagem,
        removedorManchas = removedorManchas,
        adicionais = adicionais
    )

    pedido.exibirResumo()
}