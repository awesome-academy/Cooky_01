package com.example.cooky.data.local

import androidx.room.TypeConverter
import com.example.cooky.data.local.model.nutition.Bad
import com.example.cooky.data.local.model.nutition.Good
import com.example.cooky.data.local.model.recipe.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.StringBuilder

class TypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToAnalyzedInstructions(value: String): List<AnalyzedInstruction> {
        val result = mutableListOf<AnalyzedInstruction>()
        val steps = mutableListOf<Step>()
        val stepsString = value.split(UNDERSTROKE)
        stepsString.forEach {
            val item = it.split(ASTERISK)
            steps.add(Step(item[0].toInt(), item[1]))
        }
        result.add(AnalyzedInstruction("", steps))
        return result
    }

    @TypeConverter
    fun fromAnalyzedInstructions(analyzes: List<AnalyzedInstruction>): String {
        val result = StringBuilder()
        val analyzedInstruction = analyzes[0]
        val steps = analyzedInstruction.steps
        steps.forEach {
            result.append(UNDERSTROKE).append(it.number)
                .append(ASTERISK).append(it.step)
        }
        return result.substring(1).toString()
    }

    @TypeConverter
    fun fromStringToExtendedIngredents(value: String): List<ExtendedIngredient> {
        val result = mutableListOf<ExtendedIngredient>()
        val ingredientStrings = value.split("$UNDERSTROKE")
        ingredientStrings.forEach {
            val ingredientString = it.split("$ASTERISK")
            val measureStrings = ingredientString[3]
            val itemStrings = measureStrings.split("$PLUS")
            val metric = Metric(
                amount = itemStrings[0].toDouble(),
                unitShort = itemStrings[1],
                unitLong = itemStrings[2]
            )
            val us = Us(
                amount = itemStrings[3].toDouble(),
                unitShort = itemStrings[4],
                unitLong = itemStrings[5]
            )
            val measure = Measures(metric, us)
            val ingredient = ExtendedIngredient(
                id = ingredientString[0].toInt(),
                image = ingredientString[1],
                measures = measure,
                name = ingredientString[2]
            )
            result.add(ingredient)
        }
        return result
    }

    @TypeConverter
    fun fromExtendedIngredents(list: List<ExtendedIngredient>): String {
        val result = StringBuilder()
        list.forEach {
            val measures = it.measures
            val us = measures.us
            val metric = measures.metric
            result.append(UNDERSTROKE).append(it.id)
                .append(ASTERISK).append(it.image)
                .append(ASTERISK).append(it.name)
                .append(ASTERISK).append(us.amount)
                .append(PLUS).append(us.unitShort)
                .append(PLUS).append(us.unitLong)
                .append(PLUS).append(metric.amount)
                .append(PLUS).append(metric.unitShort)
                .append(PLUS).append(metric.unitLong)
        }
        return result.substring(1).toString()
    }

    @TypeConverter
    fun fromStringToGoods(value: String): List<Good> {
        val result = mutableListOf<Good>()
        val listGoodString = value.split(UNDERSTROKE)
        listGoodString.forEach {
            val goodString = it.split(ASTERISK)
            result.add(
                Good(
                    amount = goodString[0],
                    percentOfDailyNeeds = goodString[1].toDouble(),
                    title = goodString[2]
                )
            )
        }
        return result
    }

    @TypeConverter
    fun fromGoods(listGood: List<Good>): String {
        val result = StringBuilder()
        listGood.forEach {
            listGood.forEach {
                result.append(UNDERSTROKE).append(it.amount)
                    .append(ASTERISK).append(it.percentOfDailyNeeds)
                    .append(ASTERISK).append(it.title)
            }
        }
        return result.substring(1).toString()
    }

    @TypeConverter
    fun fromStringToBads(value: String): List<Bad> {
        val result = mutableListOf<Bad>()
        val listBadString = value.split(UNDERSTROKE)
        listBadString.forEach {
            val badString = it.split(ASTERISK)
            result.add(
                Bad(
                    amount = badString[0],
                    percentOfDailyNeeds = badString[1].toDouble(),
                    title = badString[2]
                )
            )
        }
        return result
    }

    @TypeConverter
    fun fromBads(listBad: List<Bad>): String {
        val result = StringBuilder()
        listBad.forEach {
            result.append(UNDERSTROKE).append(it.amount)
                .append(ASTERISK).append(it.percentOfDailyNeeds)
                .append(ASTERISK).append(it.title)

        }
        return result.substring(1).toString()
    }

    companion object {
        private const val UNDERSTROKE = '_'
        private const val ASTERISK = '*'
        private const val PLUS = '+'
    }
}
