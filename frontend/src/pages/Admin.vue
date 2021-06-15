<!--
 * @FileDescription: 管理端页面
 * @Author: NeilKleistGao
 * @Date: 2021/6/4
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/15
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
              <q-input borderless dense debounce="300" placeholder="用户姓名" style="margin-right: 1em" v-model="users_data.search_name">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
              <q-input borderless dense debounce="300" placeholder="用户电话" style="margin-right: 1em" v-model="users_data.search_phone">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
              <q-input borderless dense debounce="300" placeholder="用户邮箱" style="margin-right: 1em" v-model="users_data.search_email">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
              <q-btn label="查询" @click="getUsersData" text-color="primary"/>
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
                  {{ props.row.bargain_id }}
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
                  <q-btn :label="'编号' + props.row.car_id" color="primary" flat @click="jump(props.row.car_id)"/>
                </q-td>
                <q-td key="price" :props="props">
                  {{ props.row.price }}
                </q-td>
                <q-td key="seller" :props="props">
                  {{props.row.seller}}
                </q-td>
                <q-td key="buyer" :props="props">
                  {{props.row.buyer}}
                </q-td>
              </q-tr>
            </template>

            <template v-slot:top-right class="row">
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
              <q-btn label="搜索" push text-color="primary" style="margin-right: 1em"/>
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
      tab: 'user',
      users_data: {
        column: [
          { name: 'id', label: '用户id', align: 'left', required: true, sortable: true, field: 'uid' },
          { name: 'name', label: '姓名', align: 'left', required: true, sortable: true, field: 'name' },
          { name: 'phone', label: '电话', align: 'left', required: true, sortable: true, field: 'phone_number' },
          { name: 'email', label: '邮箱', align: 'left', required: true, sortable: true, field: 'mail' }
        ],
        data: [],
        pagination: {
          sortBy: 'desc',
          descending: false,
          page: 1,
          rowsPerPage: 5
        },
        search_name: '',
        search_phone: '',
        search_email: ''
      },
      bargain_data: {
        column: [
          { name: 'id', label: '编号', align: 'left', required: true, sortable: true, field: 'bargain_id' },
          { name: 'start_time', label: '砍价开始时间', align: 'left', required: true, sortable: true, field: 'start_time' },
          { name: 'end_time', label: '砍价结束时间', align: 'left', required: true, sortable: true, field: 'end_time' },
          { name: 'cid', label: '砍价车辆', align: 'left', required: true, sortable: true, field: 'car_id' },
          { name: 'price', label: '买家报价', align: 'left', required: true, sortable: true, field: 'price' },
          { name: 'seller', label: '卖家', align: 'left', required: true, sortable: true, field: 'seller' },
          { name: 'buyer', label: '买家', align: 'left', required: true, sortable: true, field: 'buyer' }
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
        key_word: 'uid',
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
    },
    getUsersData () {
      let url = '/api/user/info/query_all?'
      if (this.users_data.search_name !== '') {
        url += 'name=' + this.users_data.search_name + '&'
      }
      if (this.users_data.search_phone !== '') {
        url += 'phone=' + this.users_data.search_phone + '&'
      }
      if (this.users_data.search_email !== '') {
        url += 'email=' + this.users_data.search_email + '&'
      }

      const self = this
      url = url.substring(0, url.length - 1)
      this.$axios.get(url, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        if (response.status === 200 && response.data.errCode === 0) {
          console.log(response.data)
          self.users_data.data = response.data.result
        } else {
          sessionStorage.removeItem('token')
          sessionStorage.removeItem('uid')
          window.location = '/#/admin/login'
          window.location.reload()
        }
      })
    },
    getBargainData () {
      const arg = {}
      if (this.bargain_data.value !== '') {
        arg[this.bargain_data.key_word] = this.bargain_data.value
      }

      this.$axios.post('/api/transaction/bargain/query', arg, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        console.log(response)
        if (response.status === 200 && response.data.errCode === 0) {
          this.bargain_data.data = response.data.result
          for (let i = 0; i < this.bargain_data.data.length; ++i) {
            this.$axios.get('/api/user/info/query?uid=' + this.bargain_data.data[i].uid, {
              headers: { token: sessionStorage.getItem('token') }
            }).then((response) => {
              if (response.status === 200) {
                this.bargain_data.data[i].buyer = response.data.name + ', ' + response.data.phone_number
              }
            })

            this.$axios.get('/api/car?id=' + this.bargain_data.data[i].car_id, {
              headers: { token: sessionStorage.getItem('token') }
            }).then((response) => {
              if (response.status === 200) {
                this.$axios.get('/api/user/info/query?uid=' + response.data.uid, {
                  headers: { token: sessionStorage.getItem('token') }
                }).then((response) => {
                  if (response.status === 200) {
                    this.bargain_data.data[i].seller = response.data.name + ', ' + response.data.phone_number
                  }
                })
              }
            })
          }
        } else {
          sessionStorage.removeItem('token')
          sessionStorage.removeItem('uid')
          window.location = '/#/admin/login'
          window.location.reload()
        }
      })
    }
  },
  beforeMount () {
    this.getUsersData()
    this.getBargainData()
  },
  beforeRouteEnter (from, to, next) {
    if (sessionStorage.getItem('token') === null) {
      sessionStorage.removeItem('token')
      sessionStorage.removeItem('uid')
      window.location = '/#/admin/login'
      window.location.reload()
    } else {
      next()
    }
  }
}
</script>

<style scoped>

</style>
