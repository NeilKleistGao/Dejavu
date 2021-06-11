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
                    :data="data1"
                    :columns="columns1"
                    title="个人信息"
                    :rows-per-page-options="[]"
                    row-key="name"
                  >
                    <template v-slot:body="props">
                      <q-tr :props="props">
                        <q-td key="name" :props="props">
                          {{ props.row.name }}
                        </q-td>
                        <q-td key="value" :props="props">
                          {{ props.row.value }}
                          <q-popup-edit v-model.number="props.row.value" buttons>
                            <q-input v-model.number="props.row.value" dense autofocus />
                          </q-popup-edit>
                        </q-td>
                      </q-tr>
                    </template>
                  </q-table>
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
                  <router-link>修改个人信息</router-link>
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
const columns1 = [

  { name: 'imformation', align: 'center', label: '信息名称', field: 'name' },
  { name: 'value', align: 'center', label: '值', field: 'value' }

]

const data1 = [
  {
    name: '用户ID',
    value: '23343434'
  },
  {
    name: '电话号码',
    value: '15220382083'
  },
  {
    name: '邮箱',
    value: '76079873@qq.com'
  },
  {
    name: '用户名称',
    value: 'wnn'
  }

]

export default {
  name: 'User',
  data () {
    return {
      tab: 'mails',
      splitterModel: 20,
      data1,
      columns1,
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
