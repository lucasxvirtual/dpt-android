package mapper

import com.drogariadopovo.domain.model.Token
import response.TokenResponse
import response.UserResponse
import javax.inject.Inject

class AuthMapper @Inject constructor(){

    fun map(response : TokenResponse) : Token{
        return Token(
                token = response.token
        )
    }
}