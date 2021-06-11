<!--
 * @FileDescription: 个人用户信息页面
 * @Author: wnn
 * @Date: 2021/5/28
 * @LastEditors: wnn
 * @LastEditTime: 2021/5/28
 -->
<template>
  <q-page class="flex flex-center">
    <!--ADD YOUR VUE CODE HERE-->
    <div style="height:100% ; width: 100%">
      <q-splitter
        v-model="splitterModel"
        style="height:100%"
      >

        <template v-slot:before>
          <q-tabs
            v-model="tab"
            vertical
            class="text-teal"
          >
            <q-tab name="UserInfomation" icon="img:icons/gerenxinxi.png" label="用户信息" />
            <q-tab name="Bargain"  icon="img:icons/kanjiashangcheng.png" label="砍价信息" />
            <q-tab name="Transaction" icon="img:icons/jiaoyi.png" label="交易信息"/>
            <q-tab name="CarInfo"  icon="img:icons/cheliangxinxi.png" label="卖车信息" />
          </q-tabs>
        </template>

        <template v-slot:after>
          <q-tab-panels
            v-model="tab"
            animated
            swipeable
            vertical
            transition-prev="jump-up"
            transition-next="jump-up"
            style="width: auto"
          >
            <q-tab-panel name="UserInfomation">
              <div class="text-h4 q-mb-md">个人信息</div>
              <template>
                <div class="q-pa-md" style="width: 100%">
                  <q-table
                    :data="rows"
                    row-key="信息"
                    hide-bottom
                  />
                </div>
              </template>
            </q-tab-panel>

            <q-tab-panel name="Bargain">
              <div class="text-h4 q-mb-md">砍价信息</div>
              <template>
                <div class="q-pa-md" style="width: 100%">
                  <q-table
                    title="砍价记录"
                    :data="data"
                    :columns="columns"
                    row-key="name"
                    hide-header
                    hide-bottom
                  />
                </div>
              </template>
            </q-tab-panel>

            <q-tab-panel name="Transaction">
              <div class="text-h4 q-mb-md">交易记录</div>
              <div class="q-pa-md">
                <q-table
                  title="交易记录"
                  :data=trans_data
                  :columns="trans"
                  row-key="name"
                  hide-header
                  hide-bottom
                />
              </div>
            </q-tab-panel>
            <q-tab-panel name="CarInfo">
              <div class="text-h4 q-mb-md">卖车信息</div>
              <div class="q-pa-md">
                <q-table
                  title="卖车记录"
                  :data="sale_data"
                  :columns="sales"
                  row-key="name"
                  hide-header
                  hide-bottom
                  style="width: auto"
                />
              </div>
            </q-tab-panel>
          </q-tab-panels>
        </template>

      </q-splitter>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'User',
  data () {
    return {
      tab: 'mails',
      splitterModel: 20,
      rows: [
        {
          信息: '用户id',
          值: '1234567'
        },
        {
          信息: '电话号码',
          值: '15210362956'
        },
        {
          信息: '邮箱',
          值: '74928394@qq.com'
        },
        {
          信息: '用户名称',
          值: 'wnn'
        }
      ],
      columns: [
        {
          name: 'desc',
          required: true,
          label: 'Dessert (100g serving)',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'uid', align: 'center', label: 'uid', field: 'uid', sortable: true },
        { name: 'carID', label: 'carId', field: 'carId', sortable: true },
        { name: 'pirce', label: 'price', field: 'price' },
        { name: 'start_time', label: 'start_time', field: 'start_time' },
        { name: 'end_time', label: 'end_time', field: 'sodium' }
      ],
      data: [
        {
          name: 1,
          uid: 2017213391,
          carId: 101875436,
          price: '20.3w',
          start_time: '2020/04/20 14:00',
          end_time: '2020/04/20 14:35'
        },
        {
          name: 2,
          uid: 2017213391,
          carId: 101875436,
          price: '2.3w',
          start_time: '2020/05/13 16:17',
          end_time: '2020/05/13 16:31'
        },
        {
          name: 3,
          uid: 2017213391,
          carId: 101875436,
          price: '20.3w',
          start_time: '2020/04/20 14:00',
          end_time: '2020/04/20 14:35'
        }
      ],
      trans: [
        {
          name: 'desc',
          required: true,
          label: 'Dessert',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'sale_id', align: 'center', lable: 'sale_id', field: 'sale_id' },
        { name: 'buyer_id', align: 'center', label: 'buyer_id', field: 'buyer_id' },
        { name: 'seller_id', align: 'center', label: 'seller_id', field: 'seller_id' },
        { name: 'car_id', align: 'center', label: 'car_id', field: 'car_id' },
        { name: 'deal_price', align: 'center', label: 'deal_price', field: 'deal_price', sortable: true },
        { name: 'deal_time', align: 'center', label: 'deal_time', field: 'deal_time', sortable: true }
      ],
      trans_data: [
        {
          name: 1,
          sale_id: 202104211605,
          buyer_id: 1018472973,
          seller_id: 1020472973,
          carId: 11111111,
          deal_price: 23,
          deal_time: '2020/04/21 16:05'
        },
        {
          name: 2,
          sale_id: 202104211605,
          buyer_id: 1018472973,
          seller_id: 1020472973,
          carId: 11111111,
          deal_price: 23,
          deal_time: '2020/04/21 16:05'
        }
      ],
      sales: [
        {
          name: 'sales',
          required: true,
          label: 'sales',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'carID', label: 'carId', field: 'carId', sortable: true },
        { name: 'state', label: 'state', field: 'state', sortable: true }
      ],
      sale_data: [
        {
          name: 1,
          carID: 534234632,
          state: '未售出'
        },
        {
          name: 2,
          carID: 3457845786,
          state: '已售出'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
