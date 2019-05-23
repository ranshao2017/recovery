export default {
    baseUrlForDev: 'http://127.0.0.1:8280/', // 测试环境

    baseUrlForProd: 'http://127.0.0.1:8280/', // 生产环境

    // banner
    banner: 'littleredhat//v1/get_banners',

    // 获取验证码
    getCode: 'equipment/sendSms',

    // 登录校验
    login: 'equipment/phoneCodeLogin',

    // 获取个人信息
    getUserInfo: 'equipment/getUser',

    // 投递记录
    deliveryList: 'littleredhat//v1/delivery/lists',

    // 余额明细
    walletDetailList: 'littleredhat/v1/balance/lists',

    // 帮助中心
    helpCenterList: 'littleredhat/users/v1/queryAllHelpCenter',

    // 修改个人信息
    editUserInfo: 'littleredhat/users/v1/modify',

    // 绑定银行卡
    addBank: 'littleredhat/v1/bank/add',

    // 提现
    withdrawal: 'littleredhat/v1/with_draw_bankcard',

    // 添加意见反馈
    addIdea: 'littleredhat/users/v1/saveFeedback',

    // 意见反馈列表
    ideaList: 'littleredhat/users/v1/queryFeedbackByUserId',

    // 获取openid
    getOpenId: 'littleredhat/v1/bind_wx_user_info',

    // 微信提现
    wxWithDraw: 'littleredhat/v1/with_draws'
};
