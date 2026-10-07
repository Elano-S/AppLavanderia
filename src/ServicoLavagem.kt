class ServicoLavagem(
    val tipoLavagem: Int
) {
    val valorPorKg: Double
        get() = when (tipoLavagem) {
            1 -> 15.0 // Lavagem a seco
            2 -> 12.0 // Lavagem delicada
            3 -> 10.0 // Lavagem pesada
            else -> 0.0
        }

    val nomeLavagem: String
        get() = when (tipoLavagem) {
            1 -> "Seco"
            2 -> "Delicada"
            3 -> "Pesada"
            else -> "Inválida"
        }
}