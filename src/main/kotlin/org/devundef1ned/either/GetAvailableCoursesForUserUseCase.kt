package org.devundef1ned.either

import arrow.core.*
import arrow.core.raise.either
import arrow.core.raise.ensure
import org.devundef1ned.either.model.Course
import org.devundef1ned.either.model.NetworkError

class GetAvailableCoursesForUserUseCase(
    val getUserByIdUseCase: GetUserByIdUseCase,
    val getSubscriptionsForUserUseCase: GetSubscriptionsForUserUseCase,
    val getAllSoftwareCoursesUseCase: GetAllSoftwareCoursesUseCase,
    ) {

    /**
     * Obtain user by id ->
     * Get subscriptions for this user ->
     * Check if there is a subscription for software development courses ->
     * Fetch all software development courses
     */
    fun invoke(userId: Int) : Either<CoursesError, List<Course>> = either {
        val user = getUserByIdUseCase(userId).mapLeft { it.toCoursesError() }.bind()

        val subscriptions = getSubscriptionsForUserUseCase(user)
            .mapLeft { CoursesError.Error(it) }
            .bind()

        ensure(subscriptions.any { it.type == "SOFTWARE_DEVELOPMENT_COURSES" }) {
            CoursesError.NoSoftwareCoursesSubscription
        }

        getAllSoftwareCoursesUseCase()
    }
}

sealed interface CoursesError {
    data class Error(val networkError: NetworkError) : CoursesError
    object NoSuchUser : CoursesError
    object NoSoftwareCoursesSubscription : CoursesError
}

private fun UserFetchError.toCoursesError() : CoursesError = when(this) {
    is UserFetchError.Error -> CoursesError.Error(this.networkError)
    UserFetchError.NoUserFound -> CoursesError.NoSuchUser
}
