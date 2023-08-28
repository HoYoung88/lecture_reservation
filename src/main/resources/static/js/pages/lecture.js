const lecture = {
    async init() {
        this.grid.draw();
        const response = await this.data.load();
        this.grid.setRowData(response);
    },
    grid: {
        gridOptions: {
            columnDefs: [
                { field: 'id' },
                { field: 'speakerName' },
                { field: 'venue' },
                { field: 'content' },
                {field: 'lectureTime'},
                {field: 'maxCapacity'},
                {
                    cellRenderer: ({data}) => {
                        return `<button type="button" class="btn btn-primary" data-id="${data.id}" data-bs-target="#lectureApplyModal">신청하기</button>`;
                    }
                }

            ],

        },
        draw: function () {
            const eGridDiv = document.getElementById('lectureGrid');
            new agGrid.Grid(eGridDiv, this.gridOptions);
        },
        setRowData: function (rowData) {
            this.gridOptions.api.setRowData(rowData);
        },
    },
    data: {
        load: () => {
            return new Promise((resolve, reject) => {
                $.ajax({
                    url: '/lectures',
                })
                .done(({data}) => resolve(data))
                .fail((error) => {
                    $.notify.error('데이터 조회에 실패하였습니다.');
                    reject();
                })
            });
        },
    }
}

lecture.init();

$(document).on('click', 'button[data-bs-target="#lectureApplyModal"]', function () {
    const $this = $(this);
    $('#lectureApplyModal').data('lecture-id', $this.data('id'));
    new bootstrap.Modal('#lectureApplyModal', {}).show();
})

$(document).on('click', '#btnLectureApply', function () {
    const lectureId = $('#lectureApplyModal').data('lecture-id');
    const txtEmployeeNumber = $('#txtEmployeeNumber').val();

    $.ajax({
        url: `/lectures/${lectureId}/applicants`,
        data: JSON.stringify({
            employeeNumber: txtEmployeeNumber,
        }),
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
    })
    .done(() => {
        alert('신청되었습니다.')
    })
    .fail((error) => {
        if (error.responseJSON) {
            alert(error.responseJSON.message);
        } else {
            alert('데이터 저장에 실패하였습니다.');
        }
    })
    .always(() => {
        $('#txtEmployeeNumber').val('');
        $('button[data-bs-dismiss="modal"]').trigger('click');
    });
})