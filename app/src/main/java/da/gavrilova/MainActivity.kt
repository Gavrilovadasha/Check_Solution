package da.gavrilova

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random


class MainActivity : AppCompatActivity() {
    private lateinit var operand1View: TextView
    private lateinit var operand2View: TextView
    private lateinit var resultView: TextView
    private lateinit var operationView: TextView
    private lateinit var answerRightView: TextView
    private lateinit var answerWrongView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operand1View = findViewById(R.id.Operand1)
        operand2View = findViewById(R.id.Operand2)
        resultView = findViewById(R.id.txtAnswer)
        operationView = findViewById(R.id.Operator)
        answerRightView = findViewById(R.id.AnswerRight) // Assuming this is the ID for AnswerRight
        answerWrongView = findViewById(R.id.AnswerWrong) // Assuming this is the ID for AnswerWrong



        val startButton: Button = findViewById(R.id.btnStart);
        startButton.setOnClickListener {
            generateNumbersAndOperation();
            startButton.isEnabled = false; // Отключаем кнопку "Старт" после генерации примера

        };

        val btnTrue: Button = findViewById(R.id.btnTrue); // Предполагается, что это ID для кнопки "Верно"
        btnTrue.setOnClickListener {
            incrementAnswer(answerRightView);
            startButton.isEnabled = true; // Восстанавливаем возможность нажатия кнопки "Старт" после правильного ответа
            incrementSolveExampleCount()

             val AnswerRight = findViewById<TextView>(R.id.AnswerRight)
             val SolveExamleCount = findViewById<TextView>(R.id.SolveExamleCount)
             // Вычисление статистики
             val statistics = 100 * AnswerRight.text.toString().toInt() / SolveExamleCount.text.toString().toInt()

             // Обновление текста в TextView для отображения статистики
             findViewById<TextView>(R.id.Statistics).text = statistics.toString()

        };

        val btnFalse: Button = findViewById(R.id.btnFalse); // Предполагается, что это ID для кнопки "Неверно"
        btnFalse.setOnClickListener {
            incrementAnswer(answerWrongView);
            startButton.isEnabled = true; // Восстанавливаем возможность нажатия кнопки "Старт" после неправильного ответа
            incrementSolveExampleCount()

            val AnswerRight = findViewById<TextView>(R.id.AnswerRight)
            val SolveExamleCount = findViewById<TextView>(R.id.SolveExamleCount)
            // Вычисление статистики
            val statistics = 100 * AnswerRight.text.toString().toInt() / SolveExamleCount.text.toString().toInt()

            // Обновление текста в TextView для отображения статистики
            findViewById<TextView>(R.id.Statistics).text = statistics.toString()

        };

    }

    private fun incrementSolveExampleCount() { // Функция для инкремента счетчика SolveExamleCount
        val SolveExamleCount = findViewById<TextView>(R.id.SolveExamleCount)
        val currentCount = SolveExamleCount.text.toString().toIntOrNull()?: 0
        SolveExamleCount.text = (currentCount + 1).toString()
    }

    private fun generateNumbersAndOperation() {
        val random = Random()

        // Generate random numbers between 10 and 99
        val operand1 = random.nextInt(89) + 10 // Generates a number between 10 and 99
        val operand2 = random.nextInt(89) + 10 // Generates a number between 10 and 99

        // Select a random operation
        val operations = listOf("*", "/", "-", "+")
        val operation = operations.random()

        // Perform calculation based on the operation
        var result = 0.0
        when (operation) {
            "*" -> result = operand1.toDouble() * operand2.toDouble()
            "/" -> {
                result = operand1.toDouble() / operand2.toDouble()
                if (result % 1!= 0.0) { // Check if the result is not an integer
                    result = Math.round(result * 100) / 100.0 // Round to two decimal places
                }
            }
            "-" -> result = operand1.toDouble() - operand2.toDouble()
            "+" -> result = operand1.toDouble() + operand2.toDouble()
        }

        // Update UI
        operand1View.text = operand1.toString()
        operand2View.text = operand2.toString()
        operationView.text = operation.toString()
        resultView.text = result.toString()
    }


    private fun incrementAnswer(view: TextView) {
        val currentCount = view.text.toString().toIntOrNull()?: 0
        view.text = (currentCount + 1).toString()
    }
}
