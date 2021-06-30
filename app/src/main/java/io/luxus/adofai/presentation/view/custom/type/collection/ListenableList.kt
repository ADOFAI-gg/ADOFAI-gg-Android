package io.luxus.adofai.presentation.view.custom.type.collection

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.function.Predicate
import java.util.function.UnaryOperator

class ListenableList<E> constructor(
    private val listener: Listener
) : ArrayList<E>() {

    override fun clear() {
        val prevSize = size
        super.clear()
        listener.onRemoved(0, prevSize)
    }

    override fun add(element: E): Boolean {
        val prevSize = size
        val result = super.add(element)
        if (result) listener.onInserted(prevSize)
        return result
    }

    override fun add(index: Int, element: E) {
        super.add(index, element)
        listener.onInserted(index)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val prevSize = size
        val result = super.addAll(elements)
        if (result) listener.onInserted(prevSize, elements.size)
        return result
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        val result = super.addAll(index, elements)
        if (result) listener.onInserted(index, elements.size)
        return result
    }

    override fun set(index: Int, element: E): E {
        val result = super.set(index, element)
        listener.onItemChanged(index)
        return result
    }

    override fun remove(element: E): Boolean {
        throw IllegalAccessException(cannotUseMsg("remove(element: E)"))
    }

    override fun removeAt(index: Int): E {
        val result = super.removeAt(index)
        listener.onRemoved(index)
        return result
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        throw IllegalAccessException(cannotUseMsg("removeAll(elements: Collection<E>)"))
    }

    override fun removeIf(filter: Predicate<in E>): Boolean {
        throw IllegalAccessException(cannotUseMsg("removeIf(filter: Predicate<in E>)"))
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        listener.onRemoved(fromIndex, toIndex - fromIndex)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun replaceAll(operator: UnaryOperator<E>) {
        super.replaceAll(operator)
        listener.onAllChanged()
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val result = super.retainAll(elements)
        if (result) listener.onAllChanged()
        return result
    }

    fun changeAllData(newList: Collection<E>): Boolean {
        super.clear()
        val result = super.addAll(newList)
        if (result) listener.onAllChanged()
        return result
    }

    private fun cannotUseMsg(functionName: String) =
        "Cannot use $functionName in ListenableList"

    interface Listener {
        fun onItemChanged(position: Int)
        fun onInserted(positionStart: Int, itemCount: Int = 1)
        fun onRemoved(positionStart: Int, itemCount: Int = 1)
        fun onAllChanged()
    }

}