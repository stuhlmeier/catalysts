package training.mars

import java.util.*

private class Rover(val wheelBase: Double) {

    var position = Vector2(0.0, 0.0)
        private set

    private var _angle = 0.0

    val angle get() = Math.toDegrees(_angle)

    fun turn(distance: Double, angle: Double) {
        val radius = getTurnRadius(wheelBase, angle)
        val radians = distance / radius

        val change = if (angle != 0.0) {
            val x = radius - Math.cos(radians) * radius
            val y = Math.sin(radians) * radius

            Vector2(x, y)
        } else Vector2(0.0, distance)

        position += change.rotate(Math.toDegrees(_angle))

        this._angle += radians
    }

}

fun main(): Unit = Scanner(System.`in`).use { input ->
    val rover = Rover(input.nextDouble())

    val distance = input.nextDouble()
    val steeringAngle = input.nextDouble()

    rover.turn(distance, steeringAngle)

    output(rover.position.x, rover.position.y, rover.angle.positiveAngle())
}
