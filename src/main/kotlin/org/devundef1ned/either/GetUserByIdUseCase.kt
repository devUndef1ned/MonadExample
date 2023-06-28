package org.devundef1ned.either

import arrow.core.Either
import org.devundef1ned.either.model.NetworkError
import org.devundef1ned.either.model.User

class GetUserByIdUseCase {
    operator fun invoke(id: Int): Either<UserFetchError, User> = TODO()
}

sealed interface UserFetchError {
    data class Error(val networkError: NetworkError) : UserFetchError
    object NoUserFound : UserFetchError
}