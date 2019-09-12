package com.zy.mytest

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.util.SparseArray
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.*
import com.google.gson.Gson
import com.zy.mytest.bean.Sku
import kotlinx.android.synthetic.main.activity_sku.*


class SkuActivity : AppCompatActivity() {

    lateinit var bean: Sku
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        myAdapter = MyAdapter()
        recyclerView.adapter = myAdapter
        val json =
            "{\"sku\":{\"skuBase\":{\"skus\":[{\"propPath\":\"1500:1540;1510:2027;2023:2074;2025:2075;2028:2029;2031:2032;2033:2034;2035:2056;2037:2044\",\"skuId\":4535},{\"propPath\":\"1500:2059;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2076;2037:2038\",\"skuId\":4536},{\"propPath\":\"1500:2030;1510:2027;2023:2024;2025:2026;2028:2029;2031:2032;2033:2034;2035:2036;2037:2038\",\"skuId\":4537}],\"props\":[{\"children\":[{\"id\":1540,\"name\":\"10\",\"pid\":1500},{\"id\":2030,\"name\":\"234\",\"pid\":1500},{\"id\":2059,\"name\":\"12\",\"pid\":1500}],\"id\":1500,\"name\":\"尺寸\"},{\"children\":[{\"id\":2027,\"name\":\"戒指\",\"pid\":1510}],\"id\":1510,\"name\":\"品类\"},{\"children\":[{\"id\":2024,\"name\":\"18K红钻石（白）戒指\",\"pid\":2023},{\"id\":2074,\"name\":\"0813现货1\",\"pid\":2023}],\"id\":2023,\"name\":\"商品名称\"},{\"children\":[{\"id\":2026,\"name\":\"18K红\",\"pid\":2025},{\"id\":2075,\"name\":\"驹\",\"pid\":2025}],\"id\":2025,\"name\":\"成色\"},{\"children\":[{\"id\":2029,\"name\":\"钻石（白）\",\"pid\":2028}],\"id\":2028,\"name\":\"石类\"},{\"children\":[{\"id\":2032,\"name\":\"0\",\"pid\":2031}],\"id\":2031,\"name\":\"主石重(ct)\"},{\"children\":[{\"id\":2034,\"name\":\"0\",\"pid\":2033}],\"id\":2033,\"name\":\"副石重(ct)\"},{\"children\":[{\"id\":2036,\"name\":\"234000\",\"pid\":2035},{\"id\":2056,\"name\":\"0\",\"pid\":2035},{\"id\":2076,\"name\":\"56\",\"pid\":2035}],\"id\":2035,\"name\":\"净金重(g)\"},{\"children\":[{\"id\":2038,\"name\":\"234000\",\"pid\":2037},{\"id\":2044,\"name\":\"10000\",\"pid\":2037}],\"id\":2037,\"name\":\"总重量\"}]}}}"

        bean = Gson().fromJson(json, Sku::class.java)
        myAdapter.setNewData(bean.sku.skuBase.props)

        val array = SparseArray<String>()
        bean.sku.skuBase.skus.forEach { t: Sku.SkuBean.SkuBaseBean.SkusBean ->
            array.put(t.skuId, t.propPath/* + ";"*/)
        }

        val map = SparseArray<String>()
        myAdapter.setItemClick(object :
            MyAdapter.ItemClick {
            override fun onItemClick(
                s1: String, s2: String, position: Int, isSelect: Boolean, adapter1: MyAdapter,
                adapter2: TypeAdapter
            ) {
                for (i in 0 until adapter1.data.size) {
                    val bean =
                        adapter1.getItem(i) as Sku.SkuBean.SkuBaseBean.PropsBean
                    for (j in 0 until bean.children.size) {
                        val bean2 = bean.children[j]
                        bean2.isNo = false

                    }
                }
                if (isSelect) {
                    map.put(position, "$s1:$s2")
                } else {
                    map.remove(position)
                }
                var chose = ""
                for (i in 0 until map.size()) {
                    chose += map.valueAt(i)
                }
                val list = ArrayList<Int>()
                val list1 = ArrayList<Int>()
                for (i in 0 until array.size()) {
                    val split = TextUtils.split(array.valueAt(i), ";")
                    var chose1 = ""
                    for (j in 0 until map.size()) {
                        chose1 += split[map.keyAt(j)]
                    }
                    if (chose != chose1) {
                        //得到的全部是可选择的
                        Log.e("key", i.toString())
                        list.add(i)
                    } else {
                        list1.add(i)
                    }
                }
                for (i in 0 until adapter1.data.size) {
                    val bean =
                        adapter1.getItem(i) as Sku.SkuBean.SkuBaseBean.PropsBean
                    for (j in 0 until bean.children.size) {
                        val bean2 = bean.children[j]
                        val keyString = bean2.pid.toString() + ":" + bean2.id
                        for (k in 0 until list.size) {
                            val split = TextUtils.split(array.valueAt(list[k]), ";")
                            split.forEach { s ->
                                if (s == keyString) {
                                    bean2.isNo = true
                                }
                            }

                        }
                    }
                }
                for (i in 0 until adapter1.data.size) {
                    val bean =
                        adapter1.getItem(i) as Sku.SkuBean.SkuBaseBean.PropsBean
                    for (j in 0 until bean.children.size) {
                        val bean2 = bean.children[j]
                        val keyString = bean2.pid.toString() + ":" + bean2.id
                        for (k in 0 until list1.size) {
                            val split = TextUtils.split(array.valueAt(list1[k]), ";")
                            split.forEach { s ->

                                if (s == keyString) {
                                    bean2.isNo = false
                                }
                            }

                        }
                    }
                }
                adapter1.notifyDataSetChanged()
                adapter2.notifyDataSetChanged()
               
                
            }
        })

    }

    class MyAdapter :
        BaseQuickAdapter<Sku.SkuBean.SkuBaseBean.PropsBean, BaseViewHolder>
            (R.layout.layout_select) {
        override fun convert(
            helper: BaseViewHolder,
            item: Sku.SkuBean.SkuBaseBean.PropsBean
        ) {
            helper.setText(R.id.tv_name, item.name)
            val recyclerView = helper.getView(R.id.recyclerView) as RecyclerView
            val layoutManager = FlexboxLayoutManager(mContext)
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.alignItems = AlignItems.STRETCH
            layoutManager.justifyContent = JustifyContent.FLEX_START
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)
            val adapter = TypeAdapter()
            recyclerView.adapter = adapter

            if (item.children.size == 1) {
                item.children[0].isSelect = true
            }

            adapter.setNewData(item.children)
            adapter.setOnItemClickListener { adapter, view, position ->
                val item2 =
                    adapter.getItem(position) as Sku.SkuBean.SkuBaseBean.PropsBean.ChildrenBean
                if (item2.isSelect) {
                    item2.isSelect = false
                } else {
                    for (i in 0 until item.children.size) {
                        item.children[i].isSelect = false
                    }
                    item2.isSelect = true

                }
                click?.onItemClick(
                    s1 = item.id.toString(),
                    s2 = item2.id.toString(),
                    position = helper.layoutPosition,
                    isSelect = item2.isSelect,
                    adapter1 = this@MyAdapter,
                    adapter2 = adapter as TypeAdapter
                )
                adapter.notifyDataSetChanged()

            }
        }

        var click: ItemClick? = null
        fun setItemClick(click: ItemClick) {
            this.click = click
        }

        interface ItemClick {
            fun onItemClick(
                s1: String,
                s2: String,
                position: Int,
                isSelect: Boolean,
                adapter1: MyAdapter,
                adapter2: TypeAdapter
            )
        }

    }


    class TypeAdapter :
        BaseQuickAdapter<Sku.SkuBean.SkuBaseBean.PropsBean.ChildrenBean, BaseViewHolder>
            (R.layout.item_type) {
        override fun convert(
            helper: BaseViewHolder,
            item: Sku.SkuBean.SkuBaseBean.PropsBean.ChildrenBean
        ) {
            val tvName = helper.getView(R.id.tv_type) as TextView
            tvName.text = item.name
            tvName.isSelected = item.isSelect
            if (item.isNo) {
                tvName.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent))
                tvName.isEnabled = false
            } else {
                tvName.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        mContext,
                        R.drawable.round_rectangle_bg
                    )
                )
            }
        }
    }

}
