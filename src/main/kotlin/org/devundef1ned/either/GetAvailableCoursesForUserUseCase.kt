package org.devundef1ned.either

import org.devundef1ned.either.model.*

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
    @Throws(
        ConnectionException::class,
        RequestException::class,
        NoUserFoundException::class
    )
    fun invoke(userId: Int): List<Course> {
        val user = getUserByIdUseCase(userId)
        val subscriptions = getSubscriptionsForUserUseCase(user)

        if (!subscriptions.any { it.type == "SOFTWARE_DEVELOPMENT_COURSES" }) {
            throw NoSoftwareCoursesSubscriptionException()
        }

        return getAllSoftwareCoursesUseCase()
    }
}
