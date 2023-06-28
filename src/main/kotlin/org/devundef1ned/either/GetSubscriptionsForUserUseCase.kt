package org.devundef1ned.either

import arrow.core.Either
import org.devundef1ned.either.model.NetworkError
import org.devundef1ned.either.model.Subscription
import org.devundef1ned.either.model.User

class GetSubscriptionsForUserUseCase {
    operator fun invoke(user: User): Either<NetworkError, List<Subscription>> = TODO()
}