<!--
 * @FileDescription: 管理端页面
 * @Author: NeilKleistGao
 * @Date: 2021/6/4
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/4
 -->
<template>
  <q-page class="flex">
    <q-card style="margin-top: 0.5em; min-height: 800px; width: 100%" flat>
      <q-tabs v-model="tab"
              dense
              class="text-grey"
              active-color="primary"
              indicator-color="primary"
              align="justify"
              narrow-indicator
              style="width: 20%">
        <q-tab label="用户信息查询" name="user"/>
        <q-tab label="砍价信息管理" name="bargain"/>
        <q-tab label="成交信息管理" name="transaction"/>
      </q-tabs>

      <q-tab-panels
        v-model="tab"
        animated
        transition-prev="jump-up"
        transition-next="jump-down"
        style="margin-top: 1em">
        <q-tab-panel name="user">
          <q-table title="用户查询结果"
                   :columns="users_data.column"
                   row-key="id"
                   no-data-label="没有查询到相关数据"
                   :pagination.sync="users_data.pagination"
                   :data="users_data.data">
            <template v-slot:top-right>
              <q-input borderless dense debounce="300" placeholder="用户姓名" style="margin-right: 1em">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
              <q-input borderless dense debounce="300" placeholder="用户电话" style="margin-right: 1em">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
              <q-input borderless dense debounce="300" placeholder="用户邮箱" style="margin-right: 1em">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
          </q-table>
        </q-tab-panel>

        <q-tab-panel name="bargain">
          <q-table title="砍价数据"
                   :columns="bargain_data.column"
                   row-key="id"
                   no-data-label="没有查询到相关数据"
                   :pagination.sync="bargain_data.pagination"
                   :data="bargain_data.data">
            <template v-slot:body="props">
              <q-tr :props="props">
                <q-td key="id" :props="props">
                  {{ props.row.id }}
                  <q-popup-proxy>
                    <div style="margin: 0.5em 0.5em">
                      <q-btn flat color="primary" label="标记为成交"/>
                    </div>
                  </q-popup-proxy>
                </q-td>
                <q-td key="start_time" :props="props">
                  {{ props.row.start_time }}
                </q-td>
                <q-td key="end_time" :props="props">
                  {{ props.row.end_time }}
                </q-td>
                <q-td key="cid" :props="props">
                  <q-btn :label="'编号' + props.row.cid" color="primary" flat @click="jump(props.row.cid)"/>
                </q-td>
                <q-td key="price" :props="props">
                  {{ props.row.price }}
                </q-td>
                <q-td key="seller" :props="props">
                  <q-badge v-if="props.row.seller" color="positive">已联系</q-badge>
                  <q-badge v-else color="negative">
                    未联系
                  </q-badge>
                  <q-popup-proxy>
                    <div style="margin: 0.5em 0.5em">
                      <span class="text-subtitle2">卖家姓名：{{bargain_data.current_seller.name}}</span>
                      <br/>
                      <span class="text-subtitle2">卖家电话：{{bargain_data.current_seller.phone}}</span>
                      <br/>
                      <q-btn v-if="!props.row.seller" flat color="primary" label="标记为已联系"/>
                    </div>
                  </q-popup-proxy>
                </q-td>
                <q-td key="buyer" :props="props">
                  <q-badge v-if="props.row.buyer" color="positive">已联系</q-badge>
                  <q-badge v-else color="negative">
                    未联系
                  </q-badge>
                  <q-popup-proxy>
                    <div style="margin: 0.5em 0.5em">
                      <span class="text-subtitle2">卖家姓名：{{bargain_data.current_buyer.name}}</span>
                      <br/>
                      <span class="text-subtitle2">卖家电话：{{bargain_data.current_buyer.phone}}</span>
                      <br/>
                      <q-btn v-if="!props.row.buyer" flat color="primary" label="标记为已联系"/>
                    </div>
                  </q-popup-proxy>
                </q-td>
              </q-tr>
            </template>

            <template v-slot:top-right class="row">
              <q-btn label="刷新" push text-color="primary" style="margin-right: 1em"/>
              <span style="margin-right: 1em">搜索关键字：</span>
              <q-btn-toggle v-model="bargain_data.key_word"
                            toggle-color="primary"
                            :options="[
                              {label: '用户', value: 'uid'},
                              {label: '车辆', value: 'cid'}
                            ]"
                            style="margin-right: 1em"/>
              <q-input borderless dense debounce="300" v-model="bargain_data.value" placeholder="搜索" style="margin-right: 1em">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
          </q-table>
        </q-tab-panel>

        <q-tab-panel name="transaction">
          <q-table title="成交数据"
                   :columns="transaction_data.column"
                   row-key="id"
                   no-data-label="没有查询到相关数据"
                   :pagination.sync="transaction_data.pagination"
                   :data="transaction_data.data">
            <template v-slot:body="props">
              <q-tr :props="props">
                <q-td key="sale_id" :props="props">
                  {{ props.row.sale_id }}
                  <q-popup-proxy>
                    <div style="margin: 0.5em 0.5em">
                      <q-btn flat color="negative" label="删除该交易"/>
                    </div>
                  </q-popup-proxy>
                </q-td>
                <q-td key="deal_date" :props="props">
                  {{ props.row.deal_date }}
                  <q-popup-edit v-model="props.row.deal_date">
                  </q-popup-edit>
                </q-td>
                <q-td key="buyer_id" :props="props">
                  {{ props.row.buyer_id }}
                  <q-popup-edit v-model="props.row.buyer_id">
                    <q-input v-model="props.row.buyer_id" dense autofocus />
                  </q-popup-edit>
                </q-td>
                <q-td key="seller_id" :props="props">
                  {{ props.row.seller_id }}
                  <q-popup-edit v-model="props.row.seller_id">
                    <q-input v-model="props.row.seller_id" dense autofocus />
                  </q-popup-edit>
                </q-td>
                <q-td key="car_id" :props="props">
                  编号：{{ props.row.car_id }}
                  <q-popup-edit v-model="props.row.car_id">
                    <q-input v-model="props.row.car_id" dense autofocus />
                  </q-popup-edit>
                </q-td>
                <q-td key="deal_price" :props="props">
                  {{ props.row.deal_price }}
                  <q-popup-edit v-model="props.row.deal_price">
                    <q-input type="number" v-model="props.row.deal_price" dense autofocus />
                  </q-popup-edit>
                </q-td>
              </q-tr>
            </template>

            <template v-slot:top-right class="row">
              <q-btn label="刷新" push text-color="primary" style="margin-right: 1em"/>
              <span style="margin-right: 1em">搜索关键字：</span>
              <q-btn-toggle v-model="bargain_data.key_word"
                            toggle-color="primary"
                            :options="[
                              {label: '用户', value: 'uid'},
                              {label: '车辆', value: 'cid'},
                              {label: '成交时间', value: 'time'}
                            ]"
                            style="margin-right: 1em"/>
              <q-input borderless dense debounce="300" v-model="bargain_data.value" placeholder="搜索" style="margin-right: 1em">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
          </q-table>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>
  </q-page>
</template>

<script>
export default {
  name: 'Admin',
  data () {
    return {
      tab: 'bargain',
      users_data: {
        column: [
          { name: 'id', label: '用户id', align: 'left', required: true, sortable: true, field: 'id' },
          { name: 'name', label: '姓名', align: 'left', required: true, sortable: true, field: 'name' },
          { name: 'phone', label: '电话', align: 'left', required: true, sortable: true, field: 'phone' },
          { name: 'email', label: '邮箱', align: 'left', required: true, sortable: true, field: 'email' }
        ],
        data: [],
        pagination: {
          sortBy: 'desc',
          descending: false,
          page: 1,
          rowsPerPage: 5
        }
      },
      bargain_data: {
        column: [
          { name: 'id', label: '编号', align: 'left', required: true, sortable: true, field: 'id' },
          { name: 'start_time', label: '砍价开始时间', align: 'left', required: true, sortable: true, field: 'start_time' },
          { name: 'end_time', label: '砍价结束时间', align: 'left', required: true, sortable: true, field: 'end_time' },
          { name: 'cid', label: '砍价车辆', align: 'left', required: true, sortable: true, field: 'cid' },
          { name: 'price', label: '买家报价', align: 'left', required: true, sortable: true, field: 'price' },
          { name: 'seller', label: '是否已联系卖家', align: 'left', required: true, sortable: true, field: 'seller' },
          { name: 'buyer', label: '是否已联系买家', align: 'left', required: true, sortable: true, field: 'buyer' }
        ],
        pagination: {
          sortBy: 'desc',
          descending: false,
          page: 1,
          rowsPerPage: 5
        },
        data: [
          { id: '1', start_time: '2021-6-1', end_time: '2021-7-1', cid: '114514', uid: '1919810', seller: false, buyer: true, price: 1000 }
        ],
        current_buyer: {
          name: 'takahashi',
          phone: 114514
        },
        current_seller: {
          name: 'fujiwara takuumi',
          phone: 1919810
        },
        key_word: null,
        value: ''
      },
      transaction_data: {
        column: [
          { name: 'sale_id', label: '编号', align: 'left', required: true, sortable: true, field: 'sale_id' },
          { name: 'deal_date', label: '成交时间', align: 'left', required: true, sortable: true, field: 'deal_date' },
          { name: 'buyer_id', label: '买家用户id', align: 'left', required: true, sortable: true, field: 'buyer_id' },
          { name: 'seller_id', label: '卖家用户id', align: 'left', required: true, sortable: true, field: 'seller_id' },
          { name: 'car_id', label: '砍价车辆', align: 'left', required: true, sortable: true, field: 'car_id:' },
          { name: 'deal_price', label: '成交价格', align: 'left', required: true, sortable: true, field: 'deal_price' }
        ],
        pagination: {
          sortBy: 'desc',
          descending: false,
          page: 1,
          rowsPerPage: 5
        },
        data: []
      }
    }
  },
  methods: {
    jump (id) {
      window.open('/#/car/' + id)
    }
  }
}
</script>

<style scoped>

</style>
