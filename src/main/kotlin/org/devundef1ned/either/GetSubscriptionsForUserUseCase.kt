package org.devundef1ned.either

import org.devundef1ned.either.model.ConnectionException
import org.devundef1ned.either.model.RequestException
import org.devundef1ned.either.model.Subscription
import org.devundef1ned.either.model.User

class GetSubscriptionsForUserUseCase {
    @Throws(ConnectionException::class, RequestException::class)
    operator fun invoke(user: User): List<Subscription> = TODO()
}