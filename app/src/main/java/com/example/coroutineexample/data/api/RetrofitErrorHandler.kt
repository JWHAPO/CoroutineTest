package com.example.coroutineexample.data.api

import org.xml.sax.ErrorHandler
import org.xml.sax.SAXParseException

class RetrofitErrorHandler : ErrorHandler {

    override fun error(exception: SAXParseException?) {
        print(exception?.message)
    }

    override fun warning(exception: SAXParseException?) {
        print(exception?.message)
    }

    override fun fatalError(exception: SAXParseException?) {
        print(exception?.message)
    }
}