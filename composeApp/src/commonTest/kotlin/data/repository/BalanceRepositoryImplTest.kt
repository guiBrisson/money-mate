package data.repository

import data.datasource.FakeBalanceDataSource
import domain.model.Balance
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class BalanceRepositoryImplTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: BalanceRepositoryImpl
    private lateinit var dataSource: FakeBalanceDataSource

    @BeforeTest
    fun setup() {
        dataSource = FakeBalanceDataSource()
        subject = BalanceRepositoryImpl(dataSource)
    }

    @Test
    fun `BalanceRepositoryImplTest first get users balance should be zero`() = testScope.runTest {
        assertEquals(subject.getUsersBalance(), Balance(0L))
    }

    @Test
    fun `BalanceRepositoryImplTest balance is backed by local datasource`() = testScope.runTest {
        assertEquals(
            dataSource.getUsersBalance(),
            subject.getUsersBalance()
        )
    }

    @Test
    fun `BalanceRepositoryImplTest updates users balance`() = testScope.runTest {
        val firstBalance = Balance.fromString("")
        assertEquals(subject.getUsersBalance(), firstBalance)
        subject.updateUsersBalance(firstBalance + 1000) // Adding 10
        assertEquals(subject.getUsersBalance(), Balance.fromString("10"))
    }
}
