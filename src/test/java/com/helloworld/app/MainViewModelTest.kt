package com.helloworld.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.helloworld.app.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {
    
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel()
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun testInitialGreetingLoading() = runTest {
        advanceUntilIdle()
        
        assertTrue(viewModel.isLoading.value == false)
        assertNotNull(viewModel.greetingText.value)
        assertTrue(viewModel.greetingText.value?.contains("Welcome") == true)
    }
    
    @Test
    fun testButtonClickIncrementsCount() = runTest {
        advanceUntilIdle()
        
        val initialCount = viewModel.clickCount.value ?: 0
        viewModel.onButtonClicked()
        
        assertEquals(initialCount + 1, viewModel.clickCount.value)
        assertTrue(viewModel.greetingText.value?.contains("Clicked") == true)
    }
    
    @Test
    fun testMultipleClicks() = runTest {
        advanceUntilIdle()
        
        repeat(5) {
            viewModel.onButtonClicked()
        }
        
        assertEquals(5, viewModel.clickCount.value)
    }
}