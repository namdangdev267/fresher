package com.misa.fresher.data.model.user

/**
 * - class's purpose: User authenticate body response model
 *
 * @param expiresIn for sign up
 * @param refreshToken for sign up
 *
 * @param displayName for sign in
 * @param registered for sign in
 *
 * @param error for error
 *
 * @author HTLong
 * @edit_at 21/03/2022
 */

data class UserResponse(

    val kind: String?, val localId: String?, val email: String?, val idToken: String?,

    val expiresIn: String?, val refreshToken: String?,

    val displayName: String?, val registered: Boolean?,

    val error: UserError?
)