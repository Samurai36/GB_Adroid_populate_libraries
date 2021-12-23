package com.example.gb_libs_lesson1.test.util.stub

import com.example.gb_libs_lesson1.test.util.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ScheduleProviderStub : SchedulerProvider {
    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }
}