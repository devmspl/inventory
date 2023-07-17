package com.app.inventory.Handler

import com.app.inventory.Models.RoleModel.RoleExample

interface RoleHandler {
    fun onSuccess(roleExample: RoleExample?)
    fun onError(message: String?)
}