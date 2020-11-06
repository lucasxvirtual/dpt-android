package mapper

import com.drogariadopovo.domain.model.User
import response.UserResponse
import javax.inject.Inject

class UserMapper @Inject constructor(private val branchMapper: BranchMapper, private val roleMapper: RoleMapper){

    fun map(response: List<UserResponse>) : List<User> {
        return response.map { map(it) }
    }

    fun map(response : UserResponse) : User {
        return User(
                id = response.id,
                profileImage = response.profileImage,
                email = response.email,
                name = response.name,
                allowNotification = response.allowNotification,
                branch = branchMapper.map(response.branch),
                role = roleMapper.map(response.role),
                points = response.points,
                lastLogin = response.lastLogin,
                isAdmin = response.isAdmin,
                registerDate = response.registerDate,
                updatedAt = response.updatedAt,
                forgotPasswordToken = response.forgotPasswordToken,
                forgotPasswordExpiration = response.forgotPasswordExpiration,
                phone = response.phone
        )
    }
}