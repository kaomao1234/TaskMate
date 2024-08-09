package com.example.taskmate.component.form

class Validator(private val value: Any) {
    private val errors: MutableList<String> = mutableListOf<String>()

    fun require(message: String = "This field is required"): Validator {
        if ((value as String).isEmpty()) {
            errors.add(message)
        }
        return this
    }

    fun minLength(minLength: Int, message: String = "Password must be at least $minLength characters"): Validator {
        if ((value as String).length < minLength) {
            errors.add(message)
        }
        return this
    }

    fun equalString(other:String,message: String = "is equal"):Validator{
        if(other.equals(value as String)){
            errors.add(message)
        }
        return this
    }

    fun notEqualString(other:String,message: String = "is not equal"):Validator{
        if(!other.equals(value as String)){
            errors.add(message)
        }
        return this

    }

    fun specialCharacter(message: String = "Must contain a special character"): Validator {
        if ((value as String).none { !it.isLetterOrDigit() }) {
            errors.add(message)
        }
        return this
    }

    fun emailForm(message: String = "Invalid email format"): Validator {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value as String).matches()) {
            errors.add(message)
        }
        return this
    }

    fun errorList(): List<String> {
        return this.errors.toList()
    }
}
