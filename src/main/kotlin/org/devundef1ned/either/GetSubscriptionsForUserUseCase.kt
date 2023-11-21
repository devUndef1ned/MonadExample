package org.devundef1ned.either

import org.devundef1ned.either.model.Subscription
import org.devundef1ned.either.model.User

class GetSubscriptionsForUserUseCase {
    operator fun invoke(user: User): Result<List<Subscription>> = TODO()
}