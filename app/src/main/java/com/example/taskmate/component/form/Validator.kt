package com.example.taskmate.component.form

class Validator(private val value: String) {
    private val errors: MutableList<String> = mutableListOf<String>()

    fun require(message: String = "This field is required"): Validator {
        if (value.isEmpty()) {
            errors.add(message)
        }
        return this
    }

    fun minLength(minLength: Int, message: String = "Password must be at least $minLength characters"): Validator {
        if (value.length < minLength) {
            errors.add(message)
        }
        return this
    }

    fun equalString(other:Any,message: String = "is equal"):Validator{
        if(other.equals(value)){
            errors.add(message)
        }
        return this
    }

    fun notEqualString(other:Any,message: String = "is not equal"):Validator{
        if(!other.equals(value)){
            errors.add(message)
        }
        return this

    }

    fun specialCharacter(message: String = "Must contain a special character"): Validator {
        if (value.none { !it.isLetterOrDigit() }) {
            errors.add(message)
        }
        return this
    }

    fun emailForm(message: String = "Invalid email format"): Validator {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errors.add(message)
        }
        return this
    }

    fun errorList(): List<String> {
        return this.errors.toList()
    }
}
