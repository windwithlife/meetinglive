let timeId = null;
Page({
    data: {
        history: [],
        hot: ['癌症专题讲座', '专家直播', '健康讲堂'],
        result: [
            {
                id: 1,
                url: '../details/details',
                thumb: '/image/s4.png',
                title: '专家直播1',
                price: 1
            },
            {
                id: 2,
                url: '../details/details',
                thumb: '/image/s5.png',
                title: '专家直播2',
                price: 2
            }
        ],
        showKeywords: false,
        keywords: ['疫情辽望', '专家在线2', '在线直播3', '大家想法'],
        value: '',
        showResult: false,
    },
    cancelSearch() {
        this.setData({
            showResult: false,
            showKeywords: false,
            value: ''
        })
    },
    searchInput(e) {
        if(!e.detail.value){
            this.setData({
                showKeywords: false
            })
        }else{
            if(!this.data.showKeywords){
                timeId && clearTimeout(timeId);
                timeId = setTimeout(() => {
                    this.setData({
                        showKeywords: true
                    })
                }, 1000)
            }
        }
    },
    keywordHandle(e) {
        const text = e.target.dataset.text;
        this.setData({
            value: text,
            showKeywords: false,
            showResult: true
        })
        this.historyHandle(text);
    },
    historyHandle(value) {
        let history = this.data.history;
        const idx = history.indexOf(value);
        if (idx === -1) {
            // 搜索记录只保留8个
            if (history.length > 7) {
                history.pop();
            }
        } else {
            history.splice(idx, 1);
        }
        history.unshift(value);
        wx.setStorageSync('history', JSON.stringify(history));
        this.setData({
            history
        });
    },
    onLoad() {
        const history = wx.getStorageSync('history');
        if (history) {
            this.setData({
                history: JSON.parse(history)
            })
            console.log(this.data.history);
        }
    }
})