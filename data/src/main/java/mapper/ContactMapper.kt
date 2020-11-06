package mapper

import com.drogariadopovo.domain.model.Contact
import response.ContactResponse
import javax.inject.Inject

class ContactMapper @Inject constructor(){

    fun map(response : ContactResponse) : Contact {
        return Contact(
            id = response.id,
            message = response.message,
            created_at = response.created_at,
            user = response.user
        )
    }
}