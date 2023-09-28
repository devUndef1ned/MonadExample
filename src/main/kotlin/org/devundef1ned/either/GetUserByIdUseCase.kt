package org.devundef1ned.either

import org.devundef1ned.either.model.ConnectionException
import org.devundef1ned.either.model.NoUserFoundException
import org.devundef1ned.either.model.RequestException
import org.devundef1ned.either.model.User

class GetUserByIdUseCase {

    @Throws(ConnectionException::class, RequestException::class, NoUserFoundException::class)
    operator fun invoke(id: Int): User = TODO()
}