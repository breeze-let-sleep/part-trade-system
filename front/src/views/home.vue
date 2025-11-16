<script setup>
import { ref } from 'vue'

const activeName = ref('1')
const imgs=ref(['/1.png','/2.png','/3.png','/4.png'])
const url = '/start.png'
</script>

<template>
  <h1 class="page-title">零件交易系统</h1>
  <p>
    本零件交易系统是一款专为零件行业打造的高效、便捷、透明的线上交易平台，
    核心定位为连接零件供应商与采购顾客的直接对接桥梁，致力于打破传统交易模式
    的壁垒，为双方提供全流程数字化的交易解决方案。无论是各类标准工业零件、定制
    化配件，还是稀缺维修零件，都能在系统中实现精准匹配与高效交易。
  </p>
  <h1 style="text-align: center;">四大核心优势</h1>
  <div class="demo-collapse">
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item title="成本优势显著" name="1">
        <div>
          系统彻底摒弃传统交易中的多层中间商环节，实现供应商与采购顾客的直接对接。
          供应商可直接入驻系统，经过严格的资质审核后，自主上传零件信息，包括产品
          规格、技术参数、质量认证、库存数量及报价等核心内容，无需通过中间商层层
          加价，能最大程度保留利润空间；采购顾客则可在系统中直接浏览供应商发布的
          产品信息，跳过中间商的信息筛选与传递环节，直接与供应商沟通产品细节、议
          价及确认订单。交易过程中，系统仅作为第三方平台提供交易保障与流程支撑，
          不参与交易差价分成。例如，某机械加工厂需要采购一批精密轴承，通过系统可
          直接找到生产该轴承的源头厂家，不仅能以低于市场中间商报价15%-20%的价格
          采购，还能直接与厂家沟通定制化的精度要求，大幅降低采购成本与沟通成本。
        </div>
      </el-collapse-item>
      <el-collapse-item title="服务体验优质" name="2">
        <div>
          系统配备智能客服模块，采用先进的自然语言处理技术，为用户提供7×24小时不
          间断的服务支持。智能客服可精准响应用户的常见问题，包括账号注册登录、资
          质审核流程、产品搜索技巧、订单提交与修改、支付结算方式、物流跟踪查询、
          售后问题咨询等。对于简单问题，智能客服能在10秒内给出准确答案，无需用户
          等待人工客服；对于复杂问题，如定制化零件的技术对接、大额订单的合同细节
          协商等，智能客服可快速转接至对应领域的人工客服，并同步用户前期沟通记录
          ，确保服务的连贯性与准确性。此外，智能客服还会根据用户的交易历史与咨询
          记录，主动推送个性化服务，如提醒供应商库存不足、告知顾客订单发货状态、
          推送相关零件的优惠活动等，全方位提升用户的服务体验。
        </div>
      </el-collapse-item>
      <el-collapse-item title="交易效率超高" name="3">
        <div>
          系统覆盖从产品展示、咨询沟通、订单签订、支付结算到物流跟踪、售后维权的
          完整交易链条，实现全流程数字化管理。供应商可通过系统后台实时管理产品信
          息、库存数据、订单状态，一键生成发货单并对接主流物流平台，实时同步物流
          信息；采购顾客可在线完成订单提交、支付、验收确认等操作，系统自动留存交
          易记录与凭证，便于后续对账与溯源。同时，系统具备数据统计与分析功能，供
          应商可查看产品浏览量、订单转化率、客户画像等数据，为生产与销售决策提供
          依据；顾客可查看历史采购记录，便于重复采购与成本核算，大幅提升双方的交
          易效率。
        </div>
      </el-collapse-item>
      <el-collapse-item title="安全保障全面" name="4">
        <div>
          为保障交易双方的合法权益，系统建立了严格的供应商资质审核机制。供应商入
          驻时需提交营业执照、生产许可证、质量认证证书等相关资质文件，由平台专业
          审核团队进行多维度审核，审核通过后方可开通店铺；对于进口零件，还需提供
          报关单、商检证明等文件，确保产品来源正规、质量合格。交易过程中，系统采
          用第三方支付结算方式，顾客支付的款项将暂存于第三方账户，待顾客验收合格
          并确认后，款项再划至供应商账户，有效规避“付款不发货”“发货不合格”等交易
          风险。同时，系统建立售后维权通道，若出现产品质量问题或交易纠纷，用户可
          提交相关凭证申请平台介入调解，保障双方的合法权益。
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div style="margin-top: 40px;">
    <el-carousel
      height="500px"
      direction="vertical"
      type="card"
      :autoplay="false"
    >
      <el-carousel-item v-for="item in 4" :key="item">
        <h3 text="2xl" justify="center">
          <el-image :src=imgs[item-1] :fit="contain"/>
        </h3>
      </el-carousel-item>
    </el-carousel>
  </div>
  <el-divider />
  <p style="text-align: center;">专业：软件工程 学号：10230724 网页制作：黄亿亮</p>
</template>

<style scoped>
.page-title {
  font-size: 36px;
  color: rgb(147, 71, 255);
  font-weight: bold;
}
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
