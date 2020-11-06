package mapper

import com.drogariadopovo.domain.model.QuizPagination
import response.QuizPaginationResponse
import javax.inject.Inject

class QuizPaginationMapper @Inject constructor(private val quizMapper: QuizMapper) {

    fun map(response : QuizPaginationResponse) : QuizPagination {
        return QuizPagination(
                count = response.count,
                next = response.next,
                previous = response.previous,
                results = quizMapper.map(response.results)
        )
    }

}