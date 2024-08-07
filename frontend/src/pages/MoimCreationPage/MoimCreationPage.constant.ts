import { LabeledInputProps } from '@_components/Input/MoimInput';

const MOIM_INPUT_INFOS: LabeledInputProps[] = [
  {
    name: 'title',
    title: '제목',
    type: 'text',
    placeholder: '없음',
    required: true,
  },
  {
    name: 'date',
    title: '날짜',
    type: 'date',
    placeholder: '없음',
    required: false,
  },
  {
    name: 'time',
    title: '시간',
    type: 'time',
    placeholder: '없음',
    required: false,
  },
  {
    name: 'place',
    title: '장소',
    type: 'text',
    placeholder: '없음',
    required: false,
  },
  {
    name: 'maxPeople',
    title: '최대인원수',
    type: 'number',
    placeholder: '0명',
    required: true,
  },
  {
    name: 'description',
    title: '상세 내용 작성',
    type: 'textarea',
    placeholder: '어떤 모임인지 작성해주세요!',
    required: false,
  },
] as const;

export default MOIM_INPUT_INFOS;
