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
                      <q-btn flat color="primary" label="标记为成交" @click="submitTransaction(props.row.uid, props.row.seller_id,
                       props.row.car_id, props.row.price)"/>
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
                      <q-btn flat color="negative" label="删除该交易" @click="deleteTransaction(props.row.sale_id)"/>
                    </div>
                  </q-popup-proxy>
                </q-td>
                <q-td key="deal_time" :props="props">
                  {{ props.row.deal_time }}
                  <q-popup-edit v-model="props.row.deal_time">
                    <div class="row">
                      <q-input v-model="props.row.deal_time" dense autofocus />
                      <q-btn label="保存" text-color="primary" style="margin-left: 0.5em" @click="modifyTransaction(props.row.sale_id, props.row.buyer_id,
                       props.row.seller_id, props.row.car_id, props.row.deal_price, props.row.deal_time)"/>
                    </div>
                  </q-popup-edit>
                </q-td>
                <q-td key="buyer_id" :props="props">
                  {{ props.row.buyer_id }}
                  <q-popup-edit v-model="props.row.buyer_id">
                    <div class="row">
                      <q-input v-model="props.row.buyer_id" dense autofocus />
                      <q-btn label="保存" text-color="primary" style="margin-left: 0.5em" @click="modifyTransaction(props.row.sale_id, props.row.buyer_id,
                       props.row.seller_id, props.row.car_id, props.row.deal_price, props.row.deal_time)"/>
                    </div>
                  </q-popup-edit>
                </q-td>
                <q-td key="seller_id" :props="props">
                  {{ props.row.seller_id }}
                  <q-popup-edit v-model="props.row.seller_id">
                    <q-input v-model="props.row.seller_id" dense autofocus />
                    <q-btn label="保存" text-color="primary" style="margin-left: 0.5em" @click="modifyTransaction(props.row.sale_id, props.row.buyer_id,
                       props.row.seller_id, props.row.car_id, props.row.deal_price, props.row.deal_time)"/>
                  </q-popup-edit>
                </q-td>
                <q-td key="car_id" :props="props">
                  编号：{{ props.row.car_id }}
                  <q-popup-edit v-model="props.row.car_id">
                    <div class="row">
                      <q-input v-model="props.row.car_id" dense autofocus />
                      <q-btn label="保存" text-color="primary" style="margin-left: 0.5em" @click="modifyTransaction(props.row.sale_id, props.row.buyer_id,
                       props.row.seller_id, props.row.car_id, props.row.deal_price, props.row.deal_time)"/>
                    </div>
                  </q-popup-edit>
                </q-td>
                <q-td key="deal_price" :props="props">
                  {{ props.row.deal_price }}
                  <q-popup-edit v-model="props.row.deal_price">
                    <div class="row">
                      <q-input type="number" v-model="props.row.deal_price" dense autofocus/>
                      <q-btn label="保存" text-color="primary" style="margin-left: 0.5em" @click="modifyTransaction(props.row.sale_id, props.row.buyer_id,
                       props.row.seller_id, props.row.car_id, props.row.deal_price, props.row.deal_time)"/>
                    </div>
                  </q-popup-edit>
                </q-td>
              </q-tr>
            </template>

            <template v-slot:top-right class="row">
              <q-btn label="刷新" push text-color="primary" style="margin-right: 1em"/>
              <span style="margin-right: 1em">搜索关键字：</span>
              <q-btn-toggle v-model="transaction_data.key_word"
                            toggle-color="primary"
                            :options="[
                              {label: '用户', value: 'uid'},
                              {label: '车辆', value: 'cid'},
                              {label: '成交时间', value: 'time'}
                            ]"
                            style="margin-right: 1em"/>
              <q-input borderless dense debounce="300" v-model="transaction_data.value" placeholder="搜索" style="margin-right: 1em">
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
      tab: 'user', // tab栏标签
      users_data: {
        column: [
          { name: 'id', label: '用户id', align: 'left', required: true, sortable: true, field: 'uid' },
          { name: 'name', label: '姓名', align: 'left', required: true, sortable: true, field: 'name' },
          { name: 'phone', label: '电话', align: 'left', required: true, sortable: true, field: 'phone_number' },
          { name: 'email', label: '邮箱', align: 'left', required: true, sortable: true, field: 'mail' }
        ], // 用户信息表格表头数据
        data: [], // 用户信息数据
        pagination: {
          sortBy: 'id', // 排序关键字
          descending: false, // 是否降序
          page: 1, // 当前页
          rowsPerPage: 5 // 每页数量
        }, // 翻页器数据
        search_name: '', // 搜索的名称
        search_phone: '', // 搜索的电话号码
        search_email: '' // 搜索的邮箱
      }, // 用户信息面板数据
      bargain_data: {
        column: [
          { name: 'id', label: '编号', align: 'left', required: true, sortable: true, field: 'bargain_id' },
          { name: 'start_time', label: '砍价开始时间', align: 'left', required: true, sortable: true, field: 'start_time' },
          { name: 'end_time', label: '砍价结束时间', align: 'left', required: true, sortable: true, field: 'end_time' },
          { name: 'cid', label: '砍价车辆', align: 'left', required: true, sortable: true, field: 'car_id' },
          { name: 'price', label: '买家报价', align: 'left', required: true, sortable: true, field: 'price' },
          { name: 'seller', label: '卖家', align: 'left', required: true, sortable: true, field: 'seller' },
          { name: 'buyer', label: '买家', align: 'left', required: true, sortable: true, field: 'buyer' }
        ], // 议价信息表格表头数据
        pagination: {
          sortBy: 'id', // 排序关键字
          descending: false, // 是否为降序
          page: 1, // 当前页码
          rowsPerPage: 5 // 每页数量
        }, // 翻页器数据
        data: [], // 议价数据
        key_word: 'uid', // 搜索关键字
        value: '' // 搜索值
      }, // 议价面板数据
      transaction_data: {
        column: [
          { name: 'sale_id', label: '编号', align: 'left', required: true, sortable: true, field: 'sale_id' },
          { name: 'deal_time', label: '成交时间', align: 'left', required: true, sortable: true, field: 'deal_time' },
          { name: 'buyer_id', label: '买家用户id', align: 'left', required: true, sortable: true, field: 'buyer_id' },
          { name: 'seller_id', label: '卖家用户id', align: 'left', required: true, sortable: true, field: 'seller_id' },
          { name: 'car_id', label: '砍价车辆', align: 'left', required: true, sortable: true, field: 'car_id:' },
          { name: 'deal_price', label: '成交价格', align: 'left', required: true, sortable: true, field: 'deal_price' }
        ], // 成交表格表头数据
        pagination: {
          sortBy: 'sale_id', // 排序关键字
          descending: false, // 是否为降序
          page: 1, // 当前页码
          rowsPerPage: 5 // 每页数量
        }, // 翻页器数据
        data: [], // 成交数据
        key_word: 'uid', // 搜索关键字
        value: '' // 搜索值
      } // 成交信息面板数据
    }
  },
  methods: {
    /**
     * 打开车辆信息页面
     * @param id 车辆id
     */
    jump (id) {
      window.open('/#/car/' + id)
    },
    /**
     * 要求用户重新进行登录操作
     */
    reLogin () {
      sessionStorage.removeItem('token')
      sessionStorage.removeItem('uid')
      window.location = '/#/admin/login'
      window.location.reload()
    },
    /**
     * 读取用户数据
     */
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
          self.users_data.data = response.data.result
        } else {
          this.reLogin()
        }
      })
    },
    /**
     * 读取议价数据
     */
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
                this.bargain_data.data[i].seller_id = response.data.uid
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
          this.reLogin()
        }
      })
    },
    /**
     * 读取成交数据
     */
    getTransaction () {
      const data = {}
      if (this.transaction_data.value !== '') {
        data[this.transaction_data.key_word] = this.transaction_data.value
      }

      this.$axios.post('/api/transaction/sale/query', data, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        console.log(response)
        if (response.status === 200 && response.data.errCode === 0) {
          this.transaction_data.data = response.data.result
        } else {
          this.reLogin()
        }
      })
    },
    /**
     * 提交新的成交信息
     * @param buyer 购买用户id
     * @param seller 出售用户id
     * @param car 车辆id
     * @param price 成交价格
     */
    submitTransaction (buyer, seller, car, price) {
      this.$axios.post('/api/transaction/sale/submit', {
        uid: sessionStorage.getItem('uid'),
        token: sessionStorage.getItem('token'),
        buyer_id: buyer,
        seller_id: seller,
        car_id: car,
        deal_price: price,
        deal_time: new Date().format('yyyy/MM/dd hh:mm:ss')
      }, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        if (response.status === 200 && response.data.result !== -1) {
          alert('添加交易成功')
          window.location.reload()
          this.tab = 'transaction'
        } else {
          this.reLogin()
        }
      })
    },
    /**
     * 删除成交信息
     * @param id 成交id
     */
    deleteTransaction (id) {
      this.$axios.post('/api/transaction/sale/delete', {
        sale_id: id
      }, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        if (response.status === 200 && response.data.result) {
          alert('删除成功')
          window.location.reload()
          this.tab = 'transaction'
        } else {
          this.reLogin()
        }
      })
    },
    /**
     * 修改成交信息
     * @param saleId 成交信息id
     * @param buyerId 购买用户的id
     * @param sellerId 出售用户的id
     * @param carId 车辆id
     * @param price 成交价格
     * @param date 成交时间
     */
    modifyTransaction (saleId, buyerId, sellerId, carId, price, date) {
      console.log(saleId, buyerId, sellerId, carId, price, date)
      this.$axios.post('/api/transaction/sale/modify', {
        sale_id: saleId,
        buyer_id: buyerId,
        seller_id: sellerId,
        car_id: carId,
        deal_price: price,
        deal_time: date
      }, {
        headers: {
          token: sessionStorage.getItem('token')
        }
      }).then((response) => {
        if (response.status === 200 && response.data.result) {
          alert('修改成功')
          window.location.reload()
          this.tab = 'transaction'
        } else {
          this.reLogin()
        }
      })
    }
  },
  beforeMount () {
    // eslint-disable-next-line no-extend-native
    Date.prototype.format = function (fmt) {
      const o = {
        'M+': this.getMonth() + 1,
        'd+': this.getDate(),
        'h+': this.getHours(),
        'm+': this.getMinutes(),
        's+': this.getSeconds()
      }
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
      }
      for (const k in o) {
        if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
      return fmt
    }

    this.getUsersData()
    this.getBargainData()
    this.getTransaction()
  },
  beforeRouteEnter (from, to, next) {
    if (sessionStorage.getItem('token') === null) {
      this.reLogin()
    } else {
      next()
    }
  }
}
</script>

<style scoped>

</style>
