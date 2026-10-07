

class PedidoLavanderia(
    val cliente: Cliente,
    val roupas: List<Roupa>,
    val tipoLavagem: Int,
    val removedorManchas: String?,
    val adicionais: List<Double>
) {
    private val servico = ServicoLavagem(tipoLavagem)

    fun calcularPesoTotal(): Double {
        var pesoTotal = 0.0

        for (roupa in roupas) {
            pesoTotal += roupa.pesoKg
        }

        return pesoTotal
    }

    fun calcularValorLavagem(): Double {
        return calcularPesoTotal() * servico.valorPorKg
    }

    fun calcularValorRemovedor(): Double {
        return if (removedorManchas != null) {
            15.0
        } else {
            0.0
        }
    }

    fun calcularValorAdicionais(): Double {
        var totalAdicionais = 0.0

        for (valor in adicionais) {
            totalAdicionais += valor
        }

        return totalAdicionais
    }

    fun calcularFrete(): Double {
        return if (calcularPesoTotal() >= 5.0) {
            0.0
        } else {
            10.0
        }
    }

    fun calcularTotal(): Double {
        return calcularValorLavagem() +
                calcularValorRemovedor() +
                calcularValorAdicionais() +
                calcularFrete()
    }

    fun exibirResumo() {
        val pesoTotal = calcularPesoTotal()
        val valorLavagem = calcularValorLavagem()
        val valorRemovedor = calcularValorRemovedor()
        val valorAdicionais = calcularValorAdicionais()
        val frete = calcularFrete()

        println("\n========== RESUMO DO PEDIDO ==========")
        println("Cliente: ${cliente.nome}")
        println("Tipo de lavagem: ${servico.nomeLavagem}")
        println("Valor por kg: R$ ${"%.2f".format(servico.valorPorKg)}")
        println("Peso total: ${"%.2f".format(pesoTotal)} kg")
        println("Valor da lavagem: R$ ${"%.2f".format(valorLavagem)}")
        println("Removedor de manchas: ${removedorManchas ?: "Não"}")
        println("Valor do removedor: R$ ${"%.2f".format(valorRemovedor)}")
        println("Valor dos adicionais: R$ ${"%.2f".format(valorAdicionais)}")
        println(
            "Frete: ${
                if (frete == 0.0) "Grátis"
                else "R$ %.2f".format(frete)
            }"
        )
        println("--------------------------------------")
        println("TOTAL: R$ ${"%.2f".format(calcularTotal())}")
        println("======================================")
    }
}