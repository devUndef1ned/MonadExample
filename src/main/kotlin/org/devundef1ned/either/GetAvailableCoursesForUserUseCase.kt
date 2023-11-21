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
    fun invoke(userId: Int): Result<List<Course>> {
        val userResult = getUserByIdUseCase(userId)

        if (userResult.isFailure) {
            return Result.failure(userResult.exceptionOrNull()!!)
        }

        val subscriptionsResult = getSubscriptionsForUserUseCase(userResult.getOrThrow())

        if (subscriptionsResult.isFailure) {
            return Result.failure(subscriptionsResult.exceptionOrNull()!!)
        }

        if (!subscriptionsResult.getOrDefault(emptyList()).any { it.type == "SOFTWARE_DEVELOPMENT_COURSES" }) {
            return Result.failure(NoSoftwareCoursesSubscriptionException())
        }

        return Result.success(getAllSoftwareCoursesUseCase())
    }
}
