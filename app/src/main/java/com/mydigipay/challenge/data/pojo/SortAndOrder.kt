package com.mydigipay.challenge.data.pojo

enum class Sort {
    STARS {
        override fun toString() = "stars"
    },
    FORKS {
        override fun toString() = "forks"
    },
    HELP_WANTED_ISSUES {
        override fun toString() ="help-wanted-issues"
    },
    UPDATED {
        override fun toString() ="updated"
    }
}

enum class Order {
    ASC {
        override fun toString() ="asc"
    },
    DESC {
        override fun toString() ="desc"
    }
}