<!--
 * @FileDescription: 主页文件
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: ZoeChou
 * @LastEditTime: 2021/5/9
 -->
<template>
  <q-page class="flex flex-center">
    <!--ADD YOUR VUE CODE HERE-->
    <div class="parent" style="height:50px;"></div>
    <q-form @submit="gotoSearch">
      <div class="parentSearch">
        <div class="child" style="height:70px; width:550px;">
          <q-input outlined v-model="message" label="请输入关键字，例如：君越 2.4L" style="height:50px;"/>
        </div>
        <div class="child">
          <q-btn label="搜索" v-on:click="gotoSearch" type="submit" color="primary" style="width:100px; height:55px;"/>
        </div>
      </div>
    </q-form>
    <br/>
    <div class="placeholder" style="height: 20px"></div>
    <q-card>
    <div class="parentBuySell">
      <div class="child" style="width:650px">
        <div>
          <q-btn color="blue" icon="fas fa-car" icon-right="send" label="我要买车" v-on:click="gotoSearch"/>
        </div>
        <div class="parent" style="height: 20px"></div>
        <div>
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '大众', value: '大众'},
            {label: '宝马', value: '宝马'},
            {label: '奔驰', value: '奔驰'},
            {label: '丰田', value: '丰田'},
            {label: '本田', value: '本田'},
            {label: '奥迪', value: '奥迪'},
            {label: '别克', value: '别克'},
          ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
              {label: '日产', value: '日产'},
              {label: '福特', value: '福特'},
              {label: '现代', value: '现代'},
              {label: '马自达', value: '马自达'},
              {label: '雪佛兰', value: '雪佛兰'},
              {label: '吉利', value: '吉利'},
              {label: 'Jeep', value: 'Jeep'},
            ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '3万以下', value: '3'},
            {label: '3-5万', value: '3-5'},
            {label: '5-7万', value: '5-7'},
            {label: '7-9万', value: '7-9'},
            {label: '9-12万', value: '9-12'},
            {label: '12-16万', value: '12-16'},
            {label: '16-20万', value: '16-20'},
          ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '超值', value: '3'},
            {label: '急售', value: '3-5'},
            {label: '练手车', value: '5-7'},
            {label: '准新车', value: '7-9'},
            {label: '严选车', value: '9-12'},
            {label: 'SUV', value: '12-16'},
            {label: '查看更多', value: ''},
          ]"
          />
        </div>
      </div>
      <div class="child" style="width:100px"></div>
      <div class="child" style="width:500px; height:280px;">
        <div>
          <q-btn v-on:click="gotoSell" color="blue" icon="fas fa-car-side" icon-right="send" label="我要卖车"/>
        </div>
        <div class="placeholder" style="height: 50px"></div>
        <div style="display: flex;">
          <div style="display:flex; width:200px; align-content: center; justify-content: center; align-items: stretch;">
            <div>
              <q-btn outline rounded color="primary" label="预约卖车" style="width: 180px; height:60px"/>
            </div>
          </div>
          <div style="display:flex; width:200px; align-content: center; justify-content: center; align-items: stretch;">
            <div>
              <q-btn outline rounded color="primary" label="估价" style="width: 180px; height:60px"/>
            </div>
          </div>
        </div>
      </div>
    </div>
    </q-card>
    <br/>
    <div class="outside">
      <div class="inside">
        <div class="parentQA">
          <div class="header">
            <div>
              <h4 style="font-weight: bold;">QA</h4>
            </div>
          </div>
          <div class="child1">
            <q-card style="max-width: 500px; height: 400px;">
              <q-card-section>
                <div class="text-h6">
                  <q-icon name="far fa-hand-point-right"/>
                  Dejavu二手车的车辆异地可以看车吗？
                </div>
                <div class="text-subtitle2">可以，不过请您在异地看车之前务必先和我们的销售取得联系，确认车辆的在售情况，以免您异地看车时出现车辆已售的情况耽误您的时间。</div>
              </q-card-section>

              <q-separator />

              <q-card-actions>
                <div class="text-h6">
                  <q-icon name="far fa-hand-point-right"/>
                  Dejavu二手车的优势？
                </div>
                <div class="text-subtitle2">Dejavu二手车展示车源经过259项授权检测，售出车源将依据合同约定享受平台售后保障，最高2年/4万公里。如发现售出车源是重大事故车、火烧车、水泡车、命案车，30天内可退。</div>
              </q-card-actions>

              <q-separator />
            </q-card>
          </div>
          <div class="child2">
            <q-card style="max-width: 500px; height: 400px;">
              <q-card-section>
                <div class="text-h6">
                  <q-icon name="far fa-hand-point-right"/>
                  关于交易安全及款项支付的重要提示：
                </div>
                <div class="text-subtitle2">
                  <ol>
                    <li>为保障交易安全，在Dejavu二手车进行交易必须签署正式的电子交易合同。交易合同签署过程中，请您务必提供所需交易资料和信息，仔细观看签约告知视频，严格进行人脸识别，认真阅读合同后确认签署。</li>
                    <li>Dejavu二手车禁止任何Dejavu工作人员以现金、个人微信、个人支付宝、个人银行账户刷卡或转账等方式直接收取您任何款项（包括但不限于服务费、意向金、车辆价款等）。</li>
                    <li>Dejavu二手车显著提示：交易过程中所涉及的所有款项，您均应通过Dejavu二手车提供的官方收款渠道（官方微信、官方支付宝、官方POS刷卡或对公账户转账）进行支付。</li>
                    <li>如您发现Dejavu工作人员违规操作，您应拒绝并及时向Dejavu二手车进行投诉举报（投诉举报邮箱：lianzheng.bsn@guazi.com），否则您须自行承担由此造成的任何损失。</li>
                  </ol>
                </div>
              </q-card-section>

              <q-separator />
            </q-card>
          </div>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',

  data: function () {
    return {
      message: ''
    }
  },

  methods: {
    gotoSearch () {
      this.$router.push({
        path: '/search',
        query: {
          message: this.message
        }
      })
    },
    gotoSell () {
      this.$router.push({
        path: '/sell'
      })
    }
  }
}
</script>

<style>
  .parent, .parentSearch, .parentBuySell, .placeholder {
    display: flex;
    width: 1200px;
    justify-content: center;
    align-content: center;
    align-items: stretch;
  }

  .child {
    width: 100px;
  }
  .outside {
    display:flex;
    width:1200px;
    height:500px;
    justify-content: center;
    align-content: center;
    align-items: center;
  }
  .inside {
    width:1200px;
    height:500px;
  }
  .parentQA {
    display:flex;
    flex-wrap: wrap;
    justify-content: space-between;
    width:1200px;
    height:500px;
  }
  .header {
    width:1200px;
    height: 100px;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
  }
  .child1 {
    width:600px;
    height: 400px;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
  }
  .child2 {
    width:600px;
    height: 400px;
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
  }
</style>
