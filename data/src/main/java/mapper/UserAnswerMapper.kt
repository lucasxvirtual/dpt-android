package mapper

import com.drogariadopovo.domain.model.UserAnswer
import com.drogariadopovo.domain.model.Voucher
import response.UserAnswerResponse
import response.VoucherResponse
import javax.inject.Inject

class UserAnswerMapper @Inject constructor(){
    fun map(response : List<UserAnswerResponse>) : List<UserAnswer> {
        return response.map { map(it)!! }
    }

    fun map(response : UserAnswerResponse?) : UserAnswer? {
        if(response == null)
            return null
        return UserAnswer(
                id = response.id,
                answer = response.answer,
                correct = response.correct,
                question = response.question
        )
    }
}