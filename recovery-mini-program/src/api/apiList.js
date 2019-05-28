export default {
    baseUrlForDev: 'http://127.0.0.1:8280/', // 测试环境

    baseUrlForProd: 'http://ht8.com.cn/', // 生产环境

    // 获取验证码
    getCode: 'equipment/sendSms',

    // 登录校验
    login: 'equipment/phoneCodeLogin',

    // 获取个人信息
    getUserInfo: 'equipment/getUser',

    // 扫码识别设备
    scanDevice: 'equipment/scanDevice',

    // 投递记录
    deliveryList: 'equipment/v1/delivery/lists',

    // 余额明细
    walletDetailList: 'equipment/v1/balance/lists',

    // 帮助中心
    helpCenterList: 'equipment/users/v1/queryAllHelpCenter',

    // 添加意见反馈
    addIdea: 'equipment/users/v1/saveFeedback',

    // 意见反馈列表
    ideaList: 'equipment/users/v1/queryFeedbackByUserId'
};
