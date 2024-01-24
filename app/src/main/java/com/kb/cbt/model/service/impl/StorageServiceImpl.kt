package com.kb.cbt.model.service.impl

import com.kb.cbt.model.Quiz
import com.kb.cbt.model.service.StorageService
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(): StorageService {
    override val quizList: List<Quiz>
        get() {
            TODO()
        }

    override fun getQuiz(quizId: String): Quiz? {
        TODO("Not yet implemented")
    }

    override fun saveQuiz(quiz: Quiz) {
        TODO("Not yet implemented")
    }

    override fun updateQuiz(quiz: Quiz) {
        TODO("Not yet implemented")
    }

    override fun deleteQuiz(quizId: String) {
        TODO("Not yet implemented")
    }
}